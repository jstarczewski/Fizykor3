package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GestureDetectorCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.flashcards.FlashCardsActivity
import com.clakestudio.pc.fizykor.util.obtainViewModel
import com.clakestudio.pc.fizykor.util.replaceFragmentInActivity
import com.clakestudio.pc.fizykor.util.setupActionBar
import kotlinx.android.synthetic.main.activity_equations.*
import kotlinx.android.synthetic.main.app_bar_equations.*

class EquationsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var equationsViewModel: EquationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equations)
        setSupportActionBar(toolbar)


        setupNavigationDrawer()
        setupViewFragment()

        equationsViewModel = obtainViewModel().apply {

            flashCardsEvent.observe(this@EquationsActivity, Observer<Void> {
                this@EquationsActivity.openFlashCards()
            })


        }

        setupActionBar(R.id.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }
/*
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
*/

    }

    private fun openFlashCards() {
        startActivity(Intent(this, FlashCardsActivity::class.java))
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: replaceFragmentInActivity(EquationsFragment.newInstance(), R.id.contentFrame)
    }


    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setUpDrawerContent(findViewById(R.id.nav_view))
    }

    private fun setUpDrawerContent(navigationView: NavigationView) {

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.kinematyka -> {
                    equationsViewModel.filterEquations("Rzuty")
                }
                R.id.dynamika -> {

                }

                /*
                R.id.nav_camera -> {
                    Log.e("NavDrawer", "Camera")
                }
                R.id.nav_gallery -> {
                    Log.e("NavDrawer", "Gallery")
                }*/
            }
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

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
                else -> super.onOptionsItemSelected(item)
            }

    fun obtainViewModel(): EquationsViewModel = obtainViewModel(EquationsViewModel::class.java)


}
