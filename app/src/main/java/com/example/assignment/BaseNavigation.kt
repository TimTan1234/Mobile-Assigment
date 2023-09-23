package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

abstract class BaseNavigationActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    fun initToolbarAndNavigation() {
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val nav_view: NavigationView = findViewById(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer)
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        val drawer_layout: DrawerLayout = findViewById(R.id.drawer)
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                // Handle the home action
            }
            R.id.nav_gallery -> {
                // Handle the gallery action
            }
            R.id.nav_slideshow -> {
                // Handle the slideshow action
            }
            R.id.nav_share -> {
                // Handle the share action
            }
            R.id.nav_send -> {
                // Handle the send action
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}