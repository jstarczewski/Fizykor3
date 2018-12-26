package com.clakestudio.pc.fizykor.flashcards

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.clakestudio.pc.fizykor.R
import com.clakestudio.pc.fizykor.util.obtainViewModel
import com.clakestudio.pc.fizykor.util.replaceFragmentInActivity
import com.clakestudio.pc.fizykor.util.setupActionBar
import kotlinx.android.synthetic.main.activity_flash_cards.*
import kotlinx.android.synthetic.main.app_bar_flash_cards.*

class FlashCardsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var flashCardsViewModel: FlashCardsViewModel

    private var startupCheckedItem: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_cards)
        setSupportActionBar(toolbar)

        flashCardsViewModel = obtainViewModel().apply {

            // Interactions
        }
        setupViewFragment()
        setupNavigationDrawer()

        startupCheckedItem = intent.getIntExtra("CheckedItemIndex", 0)


        setupActionBar(R.id.toolbar) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupViewFragment() {

        supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: replaceFragmentInActivity(FlashCardsFragment.newInstance(), R.id.contentFrame)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.flash_cards, menu)
        return true
    }

    private fun setupNavigationDrawer() {


        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById<NavigationView>(R.id.nav_view))
    }

    private fun setupDrawerContent(navigationView: NavigationView) {

        navigationView.setCheckedItem(startupCheckedItem)

        navigationView.setNavigationItemSelectedListener { menuItem ->

            flashCardsViewModel.filterFlashCards(menuItem.title.toString())
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun obtainViewModel(): FlashCardsViewModel = obtainViewModel(FlashCardsViewModel::class.java)


}
