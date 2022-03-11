package app.bale.demoapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.bale.demoapplication.R
import app.bale.demoapplication.databinding.ActivityMainBinding
import app.bale.demoapplication.extension.replaceFragment
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import app.bale.demoapplication.ui.dealList.DealsFragment
import app.bale.demoapplication.ui.profile.ProfileFragment

/**
 * Not extended with BaseActivity as we don't need ViewModel for this activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment Handling
        val dealsFragment = DealsFragment()
        val profileFragment = ProfileFragment()
        setCurrentFragment(dealsFragment)

        setScreenTitle(getString(R.string.deals_title))

        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.deals -> {
                    if( binding.navView.selectedItemId != R.id.deals) {
                        supportFragmentManager.popBackStack(
                            profileFragment.javaClass.simpleName,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        setCurrentFragment(dealsFragment)
                        setScreenTitle(getString(R.string.deals_title))
                    }
                }
                R.id.profile -> {
                    if( binding.navView.selectedItemId != R.id.profile) {
                        supportFragmentManager.popBackStack(
                            dealsFragment.javaClass.simpleName,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        setCurrentFragment(profileFragment)
                        setScreenTitle(getString(R.string.profile_title))                    }
                }
            }
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true) // show back button
                supportActionBar?.setHomeButtonEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
            setTitleOfFragment()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }
        else{
            super.onBackPressed()
        }
    }

    private fun AppCompatActivity.setScreenTitle(title: String) {
        actionBar?.title = title
        this.title = title
    }

    private fun setTitleOfFragment() {

        if(supportFragmentManager.backStackEntryCount >= 1) {

            val backEntry : FragmentManager.BackStackEntry  = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag: String? = backEntry.name
            when(tag) {
                DealsFragment::class.simpleName -> {
                    setScreenTitle(getString(R.string.deals_title))
                }
                DealDetailsFragment::class.simpleName -> {
                    setScreenTitle(getString(R.string.deal_details_title))
                }
                ProfileFragment::class.simpleName -> {
                    setScreenTitle(getString(R.string.profile_title))
                }
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        replaceFragment(fragment, R.id.nav_host_fragment_activity_main)


}