package com.szakdolgozat.mygrades.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.RectF
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.google.android.material.navigation.NavigationView
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseActivity
import com.szakdolgozat.mygrades.model.Talking
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.ui.addgrade.AddGradeFragment
import com.szakdolgozat.mygrades.ui.addsubject.addSubjectFragment
import com.szakdolgozat.mygrades.ui.chat.ChatFragment
import com.szakdolgozat.mygrades.ui.grades.GradesFragment
import com.szakdolgozat.mygrades.ui.login.LoginActivity
import com.szakdolgozat.mygrades.ui.newsubject.NewSubjectFragment
import com.szakdolgozat.mygrades.ui.newtalking.NewTalkingFragment
import com.szakdolgozat.mygrades.ui.profil.ProfileFragment
import com.szakdolgozat.mygrades.ui.subjectdetails.SubjectDetailsFragment
import com.szakdolgozat.mygrades.ui.subjects.SubjectsFragment
import com.szakdolgozat.mygrades.ui.talking.TalkingFragment
import com.szakdolgozat.mygrades.util.FormatDate
import com.szakdolgozat.mygrades.util.ImageProvider
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : BaseActivity<MainPresenter, MainView>(), NavigationView.OnNavigationItemSelectedListener, MainView,
    MonthLoader.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {

    lateinit var navView: NavigationView
    lateinit var navName: TextView
    lateinit var navAvatar: ImageView
    lateinit var navEmail: TextView
    lateinit var navHeader: View
    lateinit var mWeekView: WeekView

    var selectedmenuItem: Int = R.id.nav_Timetable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolBar()

        initViews()

        initCalender()

        presenter?.getUser()
        presenter?.newEvent()
    }

    fun setToolBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun getProgressBarScreen(): View {
        return progress_bar_screen
    }

    override fun getFragmentContainerId(): Int {
        return R.id.main_fragment
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    private fun initViews() {
        navView = findViewById(R.id.nav_view) as NavigationView
        navHeader = navView.getHeaderView(0)
        navAvatar = navHeader.findViewById(R.id.imageView)
        navName = navHeader.findViewById(R.id.nav_Name)
        navEmail = navHeader.findViewById(R.id.nav_Email)

        navView.getMenu().getItem(1).isChecked = true
        navView.setNavigationItemSelectedListener(this)
    }

    private fun initCalender() {
        mWeekView = findViewById(R.id.weekView) as WeekView
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
    }

    override fun onBackPressed() {
        if (drawerLayout != null) {
            if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                navView.getMenu().getItem(1).isChecked = true
                hideFragment()
                selectedmenuItem = R.id.nav_Timetable
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                if (!(User.person?.getuserId().equals("offline"))) {
                    presenter?.UserLogOut()
                    return true
                } else
                    return false
            }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_Profile -> {
                navView.getMenu().getItem(0).isChecked = true
                showFragment(ProfileFragment())
                selectedmenuItem = R.id.nav_Profile
            }
            R.id.nav_Timetable -> {
                navView.getMenu().getItem(1).isChecked = true
                hideFragment()
                selectedmenuItem = R.id.nav_Timetable
            }
            R.id.nav_Subjects -> {
                navView.getMenu().getItem(2).isChecked = true
                showFragment(SubjectsFragment())
                selectedmenuItem = R.id.nav_Subjects
            }
            R.id.nav_Grades -> {
                navView.getMenu().getItem(3).isChecked = true
                showFragment(GradesFragment())
                selectedmenuItem = R.id.nav_Grades
            }
            R.id.nav_Settings -> {
                //Todo
            }

            R.id.nav_Messages -> {
                navView.getMenu().getItem(4).isChecked = true
                showFragment(ChatFragment())
                selectedmenuItem = R.id.nav_Messages
            }
        }

        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {
        val eventFragment = SubjectDetailsFragment(event?.name ?: "")
        eventFragment.show(supportFragmentManager, "Details")
    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {}

    override fun onMonthChange(newYear: Int, newMonth: Int): List<WeekViewEvent> {
        mWeekView.goToHour(Calendar.getInstance()[Calendar.HOUR].toDouble())
        return presenter!!.getEvents(newYear, newMonth)
    }


    override fun userLoggedOut() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun setUserOnDrawer() {
        if (User.loggedIn) {
            navAvatar.setImageBitmap(User.avatar ?: (BitmapFactory.decodeResource(resources, R.drawable.profil)))
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

    override fun showAddNewSubjectFragment() {
        showFragment(addSubjectFragment())
    }

    override fun showCreateNewSubjectFragment() {
        showFragment(NewSubjectFragment())
    }

    fun showNewSubjectFragment() {
        presenter?.addSubjectbyUserType()
    }

    fun showAddGradeFragment() {
        showFragment(AddGradeFragment())
    }

    fun showTalkingFragment(talking: Talking) {
        showFragment(TalkingFragment(talking))
    }

    fun ShowNewTalkingFragment() {
        showFragment(NewTalkingFragment())
    }

    fun returnFromAddGradeFragment() {
        navView.getMenu().getItem(3).isChecked = true
        showFragment(GradesFragment())
    }

    fun returnFromNewSubjectFragment() {
        navView.getMenu().getItem(2).isChecked = true
        showFragment(SubjectsFragment())
    }


    fun returnFromNewTalkingFragment() {
        navView.getMenu().getItem(4).isChecked = true
        showFragment(ChatFragment())
    }


    fun refreshCalendar() {
        presenter?.refreshEvent()
        onMonthChange(FormatDate.getYear(), 0)
        mWeekView.notifyDatasetChanged()
    }

}
