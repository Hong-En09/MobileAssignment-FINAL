package com.example.mobileassignment.ui.CustomerFolder

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
import com.example.mobileassignment.databinding.ActivityCustomerBinding
import com.example.mobileassignment.ui.UserManagement.UserActivity
import com.google.android.material.navigation.NavigationView

class CustomerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarCustomer.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_customer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_productList, R.id.nav_acceptedRequestList
            ), drawerLayout
        )

        //Session Function
        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val username = sharedPreferences?.getString("username", null)
        val email = sharedPreferences?.getString("email", null)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        val headerView = navigationView.getHeaderView(0)
        //val drawerImage: ImageView = headerView.findViewById<View>(R.id.drawer_image) as ImageView
        val drawerUsername = headerView.findViewById<View>(R.id.usernameCustomerDrawerText) as TextView
        val drawerAccount = headerView.findViewById<View>(R.id.emailCustomerDrawerText) as TextView
        //drawerImage.setImageDrawable(R.drawable.ic_user)
        drawerUsername.text = username.toString()
        drawerAccount.text = email.toString()

        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener { _ ->
            //edit shared preference
            val logout: SharedPreferences.Editor = sharedPreferences.edit()
            logout.clear()
            logout.apply()
            finish()
            startActivity(Intent(applicationContext, UserActivity::class.java))
            true
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.customer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_customer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}