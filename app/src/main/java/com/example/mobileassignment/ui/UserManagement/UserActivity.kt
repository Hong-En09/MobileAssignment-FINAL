package com.example.mobileassignment.ui.UserManagement


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class UserActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var appBarConfiguration1: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var navigationView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_welcome,
                R.id.nav_productList,
                R.id.nav_profile,
                R.id.nav_farmerHomepage,
                R.id.nav_customerRequestList
            ), drawerLayout
        )
        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val username = sharedPreferences?.getString("username", null)
        val email = sharedPreferences?.getString("email", null)

        val navigationView = findViewById(R.id.nav_view) as NavigationView

        val headerView = navigationView.getHeaderView(0)
        //val drawerImage: ImageView = headerView.findViewById<View>(R.id.drawer_image) as ImageView
        val drawerUsername = headerView.findViewById<View>(R.id.usernameDrawerText) as TextView
        val drawerAccount = headerView.findViewById<View>(R.id.emailDrawerText) as TextView
        //drawerImage.setImageDrawable(R.drawable.ic_user)
        drawerUsername.text = username.toString()
        drawerAccount.text = email.toString()

        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener { _ ->
            val logout: SharedPreferences.Editor = sharedPreferences.edit()
            logout.clear()
            logout.apply()
            startActivity(Intent(applicationContext, UserActivity::class.java))
            finish()
            true
        }



        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        //val nav_Menu: Menu = navigationView!!.getMenu()
        //nav_Menu.findItem(R.id.nav_productList).isVisible = false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)


        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}