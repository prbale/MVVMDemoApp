package app.bale.demoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.bale.demoapplication.databinding.ActivityMainBinding
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
                    setCurrentFragment(dealsFragment)
                    actionBar?.title = "Deals"
                    title = "Deals"
                }
                R.id.profile -> {
                    setCurrentFragment(profileFragment)
                    actionBar?.title = "Profile"
                    title = "Profile"
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_main, fragment)
            show(fragment)
            commit()
        }
}