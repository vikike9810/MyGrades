/*
package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.util.TestHelper
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class H_ChechGradesTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun chechGradesTest() {

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

        TestHelper.waitForUI(4)

        TestHelper.logOut()

        TestHelper.waitForUI(1)

        TestHelper.loginTest("pelda@grades.com", "123456")

        TestHelper.waitForUI(1)

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
                    4
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        TestHelper.waitForUI(1)

        val frameLayout = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.grade_recycler),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val textView = onView(
            allOf(
                withId(R.id.grade_teacher), withText("Pelda Jama")
            )
        )
        textView.check(matches(withText("Pelda Jama")))

        TestHelper.waitForUI(1)

        val textView2 = onView(
            allOf(
                withId(R.id.grade_subject), withText("new")
            )
        )
        textView2.check(matches(withText("new")))

        TestHelper.waitForUI(1)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.Filter_arrow),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Filter_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.Subject_spinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(1)
        appCompatTextView.perform(click())

        TestHelper.waitForUI(1)

        val appCompatButton = onView(
            allOf(
                withId(R.id.grade_go_button)
            )
        )
        appCompatButton.perform(click())

        TestHelper.waitForUI(1)

        val frameLayout2 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.grade_recycler),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val appCompatImageButton2 = onView(
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
        appCompatImageButton2.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView2.perform(click())

        TestHelper.waitForUI(1)

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
