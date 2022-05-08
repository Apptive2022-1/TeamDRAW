package com.example.teamdraw

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.teamdraw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        setContentView(R.layout.activity_main)

=======
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController =this.findNavController(R.id.nav_host_fragment)
//        NavigationUI.setupWithNavController(this, navController)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        val navHostController = navHostFragment.navController

        binding.bottomNavBar.setupWithNavController(navHostController)
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contestDetailFragment -> bottomNavBarHide()
                else -> bottomNavBarShow()
            }
        }

    }

    private fun bottomNavBarHide() {
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun bottomNavBarShow() {
        binding.bottomNavBar.visibility = View.VISIBLE
>>>>>>> mainView
    }
}