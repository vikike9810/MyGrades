package com.szakdolgozat.mygrades.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.WeekView
import com.szakdolgozat.mygrades.model.User
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.MonthLoader
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.login.LoginActivity
import com.szakdolgozat.mygrades.ui.profil.ProfileFragment
import com.szakdolgozat.mygrades.util.BitmapTransformations
import com.szakdolgozat.mygrades.util.ImageProvider


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView, MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {


    private var presenter : MainPresenter? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var navName: TextView
    lateinit var navAvatar: ImageView
    lateinit var navEmail: TextView
    lateinit var navHeader: View
    lateinit var  mWeekView: WeekView
    lateinit var LoggedInLayout: ConstraintLayout
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
        navAvatar=navHeader.findViewById(R.id.imageView)
        navName=navHeader.findViewById(R.id.nav_Name)
        navEmail=navHeader.findViewById(R.id.nav_Email)
        LoggedInLayout=navHeader.findViewById(R.id.layout_LoggedIn)
        profileFragment= ProfileFragment()
        initCalender()


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.getMenu().getItem(1).isChecked = true

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
        when (item.itemId) {
            R.id.nav_Profile -> {
                navView.getMenu().getItem(0).isChecked = true
                actualFragment=profileFragment
                supportFragmentManager.beginTransaction().add(R.id.main_fragment,profileFragment).addToBackStack("Profile").commit()
            }
            R.id.nav_Timetable -> {
                navView.getMenu().getItem(0).isChecked = true
                actualFragment?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
                actualFragment=null
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


    override fun userLoggedOut() {
       startActivity(Intent(this,LoginActivity::class.java))
    }

    override fun setUserOnDrawer() {
        if(User.loggedIn) {
            navAvatar.setImageBitmap(User.avatar?: (BitmapFactory.decodeResource(resources, R.drawable.profil)))
            navName.text = User.Name
            navEmail.text = User.email
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (resultCode == Activity.RESULT_OK && requestCode == ImageProvider.PICK_IMAGE) {
             ImageProvider.imageSelected(data, this)
         }
     }
}
