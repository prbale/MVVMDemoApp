package app.bale.demoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.bale.demoapplication.databinding.ActivityMainBinding
import app.bale.demoapplication.extension.inTransaction
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.profile.ProfileFragment


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

        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.deals -> {
                    if( binding.navView.selectedItemId != R.id.deals ) {
                        supportFragmentManager.popBackStack(
                            profileFragment.javaClass.name,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        setCurrentFragment(dealsFragment)
                        actionBar?.title = "Deals"
                        title = "Deals"
                    }
                }
                R.id.profile -> {
                    if( binding.navView.selectedItemId != R.id.profile ) {
                        supportFragmentManager.popBackStack(
                            dealsFragment.javaClass.name,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        setCurrentFragment(profileFragment)
                        actionBar?.title = "Profile"
                        title = "Profile"
                    }
                }
            }
            true
        }

        supportFragmentManager.addOnBackStackChangedListener {

            println("backStackEntryCount -> ${supportFragmentManager.backStackEntryCount}")

            if (supportFragmentManager.backStackEntryCount > 1) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true) // show back button
                supportActionBar?.setHomeButtonEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
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

    private fun setTitleOfFragment() {

        val index = supportFragmentManager.backStackEntryCount
        val backEntry : FragmentManager.BackStackEntry  = supportFragmentManager.getBackStackEntryAt(index)
        val tag: String? = backEntry.name
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)

        when(fragment) {
            is DealsFragment -> {
                supportActionBar?.title = "Deals"
            }
            is DealDetailsFragment -> {
                supportActionBar?.title = "Deal Details"
            }
            is ProfileFragment -> {
                supportActionBar?.title = "Profile"
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            replace(R.id.nav_host_fragment_activity_main, fragment)
            addToBackStack(fragment.javaClass.name)
        }
    }
}