package com.clakestudio.pc.fizykor.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.clakestudio.pc.fizykor.DrawerLiveEvent
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.util.InfoActivity
import com.clakestudio.pc.fizykor.util.setupActionBar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_equations.*
import kotlinx.android.synthetic.main.app_bar_equations.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var drawerLiveEvent: DrawerLiveEvent<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equations)
        setSupportActionBar(toolbar)

        drawerLiveEvent.setOwner(this)

        setupNavigationDrawer()

        setupActionBar(R.id.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            title = resources.getString(R.string.kinematyka)
            setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.equations, menu)
        return true
    }


    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setUpDrawerContent(findViewById(R.id.nav_view))
    }

    private fun setUpDrawerContent(navigationView: NavigationView) {

        navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.stale -> fab.hide()
                R.id.przedrostki -> fab.hide()
                else -> fab.show()
            }
            menuItem.isChecked = true
            toolbar.title = menuItem.title.toString()
            drawer_layout?.closeDrawers()
            drawerLiveEvent.value = menuItem.title.toString()
            true
        }
        navigationView.menu.findItem(R.id.stale_i_przedrostki).isVisible = true

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
            when (item?.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
                R.id.action_info -> {
                    startActivity(Intent(this, InfoActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

}
