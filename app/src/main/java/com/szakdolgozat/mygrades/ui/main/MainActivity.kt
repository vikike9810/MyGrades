package com.szakdolgozat.mygrades.ui.main

import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.WeekView
import com.szakdolgozat.mygrades.model.User
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.MonthLoader
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.login.LoginActivity
import com.szakdolgozat.mygrades.ui.profil.ProfileFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView, MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {


    private var presenter : MainPresenter? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var navName: TextView
    lateinit var navEmail: TextView
    lateinit var navHeader: View
    lateinit var  mWeekView: WeekView
    lateinit var LoggedInLayout: LinearLayout
    lateinit var NotLoggedInLayout: LinearLayout
    lateinit var profileFragment :ProfileFragment
    var actualFragment: Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        navView = findViewById(R.id.nav_view) as NavigationView
        navHeader= navView.getHeaderView(0)
        navName=navHeader.findViewById(R.id.nav_Name)
        navEmail=navHeader.findViewById(R.id.nav_Email)
        LoggedInLayout=navHeader.findViewById(R.id.layout_LoggedIn)
        NotLoggedInLayout=navHeader.findViewById(R.id.layout_NotLoggedIn)
        profileFragment= ProfileFragment()
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
        presenter?.newEvent(2019,10)
    }

    fun initCalender(){
        mWeekView= findViewById(R.id.weekView) as WeekView
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
         when (item.itemId) {
            R.id.action_logout -> {presenter?.UserLogOut()
             return true}
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_Profile -> {
                supportFragmentManager.beginTransaction().add(R.id.main_fragment,profileFragment).addToBackStack("Profile").commit()
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

    fun OnclickLogIn(v: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun OnclickLogOut(v: View){
        presenter?.UserLogOut()
    }

    override fun userLoggedOut() {
       startActivity(Intent(this,LoginActivity::class.java))
    }

    override fun setUserOnDrawer(user: User) {
        if(user.loggedIn) {
            LoggedInLayout.visibility=View.VISIBLE
            NotLoggedInLayout.visibility=View.GONE
            navName.text = user.Name
            navEmail.text = user.email
        }
        else{
            LoggedInLayout.visibility=View.GONE
            NotLoggedInLayout.visibility=View.VISIBLE
        }
    }
}
