package com.example.teamdraw.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login() // 자동 로그인

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

    private fun login(){
        if (auth.currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
        }
        // 로그인이 되어있지 않으면 loginActivity 호출
    }

    private fun bottomNavBarHide() {
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun bottomNavBarShow() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }
}