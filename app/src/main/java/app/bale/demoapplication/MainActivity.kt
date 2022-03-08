package app.bale.demoapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.bale.demoapplication.databinding.ActivityMainBinding
import app.bale.demoapplication.extension.inTransaction
import app.bale.demoapplication.extension.replaceFragment
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import app.bale.demoapplication.ui.deals.DealsFragment
import app.bale.demoapplication.ui.profile.ProfileFragment
import dagger.android.AndroidInjection


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
        actionBar?.title = "Deals"
        title = "Deals"

        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.deals -> {
                    if( binding.navView.selectedItemId != R.id.deals ) {
                        supportFragmentManager.popBackStack(
                            profileFragment.javaClass.simpleName,
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
                            dealsFragment.javaClass.simpleName,
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

    private fun setTitleOfFragment() {

        if(supportFragmentManager.backStackEntryCount >= 1) {

            val backEntry : FragmentManager.BackStackEntry  = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag: String? = backEntry.name
            when(tag) {
                "DealsFragment" -> {
                    supportActionBar?.title = "Deals"
                    title = "Deals"
                }
                "DealDetailsFragment" -> {
                    supportActionBar?.title = "Deal Details"
                    title = "Deal Details"
                }
                "ProfileFragment" -> {
                    supportActionBar?.title = "Profile"
                    title = "Profile"
                }
            }


        }
        else{

        }
        actionBar?.title = "Deal Details"
        title = "Deal Details"


    }

    private fun setCurrentFragment(fragment: Fragment) {
        replaceFragment(fragment, R.id.nav_host_fragment_activity_main)
    }

}