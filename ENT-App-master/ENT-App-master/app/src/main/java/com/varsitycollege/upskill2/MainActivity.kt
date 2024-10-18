package com.varsitycollege.upskill2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.varsitycollege.upskill2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        // Set up the AppBarConfiguration for handling the navigation drawer and up button behavior
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_courses,
                R.id.nav_cart,
                R.id.nav_signup,
                R.id.nav_contactus,
                R.id.nav_paymentmethod
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set up NavigationItemSelectedListener for the drawer menu items
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.nav_home)
                }
                R.id.nav_courses -> {
                    navController.navigate(R.id.nav_courses)
                }
                R.id.nav_signup -> {
                    navController.navigate(R.id.nav_signup)
                }
                R.id.nav_contactus -> {
                    navController.navigate(R.id.nav_contactus)
                }
                R.id.nav_cart -> {
                    navController.navigate(R.id.nav_cart)
                }
                R.id.nav_paymentmethod -> {
                    navController.navigate(R.id.nav_paymentmethod)
                }
            }
            drawerLayout.closeDrawers()  // Close the drawer after a selection is made
            true
        }
    }

    // Handle navigation with the "Up" button in the ActionBar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
