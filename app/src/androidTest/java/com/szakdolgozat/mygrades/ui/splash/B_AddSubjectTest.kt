/*
package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.szakdolgozat.mygrades.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.szakdolgozat.mygrades.util.TestHelper


@LargeTest
@RunWith(AndroidJUnit4::class)
class B_AddSubjectTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)



    @Test
    fun addSubjectTest() {

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

        TestHelper.waitForUI(5)


        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        TestHelper.waitForUI(1)

        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_subject_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_fragment),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        TestHelper.waitForUI(1)

        val textInputEditText = onView(
            allOf(
                withId(R.id.new_sub_Name)
            )
        )
        textInputEditText.perform(replaceText("new"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.new_sub_desc)
            )
        )
        textInputEditText2.perform(replaceText("new subject"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.lesson_type)
            )
        )
        appCompatSpinner.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(1)
        appCompatTextView.perform(click())

        TestHelper.waitForUI(1)

        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.new_less_monday)
            )
        )
        appCompatCheckBox.perform(click())

        TestHelper.waitForUI(1)

        TestHelper.setDate(R.id.first_lesson, 2019, 11, 11);
        TestHelper.setDate(R.id.last_lesson, 2020, 1, 11);

        TestHelper.waitForUI(1)

        TestHelper.setTime(R.id.monday_from, 8,15)
        TestHelper.setTime(R.id.monday_to, 10,15)


        TestHelper.waitForUI(1)

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.subject_save)
            )
        )
        appCompatButton5.perform(scrollTo(), click())

        TestHelper.waitForUI(1)

        val linearLayout = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Subject_list),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
*/
