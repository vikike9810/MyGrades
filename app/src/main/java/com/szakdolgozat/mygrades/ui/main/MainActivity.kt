package com.szakdolgozat.mygrades.ui.main

import android.graphics.RectF
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alamkanak.weekview.WeekView
import com.szakdolgozat.mygrades.model.User
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.MonthLoader
import com.szakdolgozat.mygrades.R
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView, MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {


    private var presenter : MainPresenter? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var navName: TextView
    lateinit var navEmail: TextView
    lateinit var navHeader: View
    lateinit var  mWeekView: WeekView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navHeader= navView.getHeaderView(0)
        navName=navHeader.findViewById(R.id.nav_Name)
        navEmail=navHeader.findViewById(R.id.nav_Email)
        initCalender()


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.getMenu().getItem(1).isChecked = true;

        navView.setNavigationItemSelectedListener(this)
        presenter = MainPresenter(this)
        presenter?.getUser()
        presenter?.newEvent(2019,9)
    }

    fun initCalender(){
        mWeekView= findViewById(R.id.weekView)
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_Profile -> {

            }
            R.id.nav_Timetable -> {

            }
            R.id.nav_Subjects -> {

            }
            R.id.nav_Grades -> {

            }
            R.id.nav_Settings -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        Toast.makeText(this,"Esemeny",Toast.LENGTH_SHORT).show()
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {
        Toast.makeText(this,"Esemeny",Toast.LENGTH_SHORT).show()
    }

    override fun onMonthChange(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        mWeekView.goToHour(7.0)
        return presenter!!.getEvents(newYear, newMonth)
    }

    override fun setUserOnDrawer(user: User) {
        navName.text = user.Name
        navEmail.text = user.email
    }
}
