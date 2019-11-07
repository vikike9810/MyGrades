package com.szakdolgozat.mygrades.util

import android.R
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.BoundedMatcher
import com.szakdolgozat.mygrades.recyclerview.adapter.SubjectsRecyclerViewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher


object TestHelper {

    fun setDate(datePickerLaunchViewId: Int, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Espresso.onView(ViewMatchers.withId(datePickerLaunchViewId)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(
            PickerActions.setDate(
                year,
                monthOfYear,
                dayOfMonth
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
    }

    fun setTime(timePickerLaunchViewId: Int, hour: Int, min: Int) {
        Espresso.onView(ViewMatchers.withId(timePickerLaunchViewId)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker::class.java.name))).perform(
            PickerActions.setTime(
                hour,
                min
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click())
    }

    fun waitForUI(num: Long){
        Thread.sleep((num*2000))
    }

    fun loginTest(email: String, passw:String){

        waitForUI(1)

        val appCompatEditText4 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(com.szakdolgozat.mygrades.R.id.Login_Email)
            )
        )
        appCompatEditText4.perform(ViewActions.click())

       waitForUI(1)

        val appCompatEditText5 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(com.szakdolgozat.mygrades.R.id.Login_Email)
            )
        )
        appCompatEditText5.perform(ViewActions.replaceText(email), ViewActions.closeSoftKeyboard())

       waitForUI(1)

        val appCompatEditText6 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(com.szakdolgozat.mygrades.R.id.Login_Passw)
            )
        )
        appCompatEditText6.perform(ViewActions.replaceText(passw), ViewActions.closeSoftKeyboard())

        waitForUI(1)

        val appCompatImageView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(com.szakdolgozat.mygrades.R.id.Butt_Login)
            )
        )
        appCompatImageView.perform(ViewActions.click())

    }

        fun logOut(){
            val actionMenuItemView = Espresso.onView(
                Matchers.allOf(
                    ViewMatchers.withId(com.szakdolgozat.mygrades.R.id.action_logout),
                    ViewMatchers.withContentDescription("Logout")
                )
            )
            actionMenuItemView.perform(click())
        }


    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

}