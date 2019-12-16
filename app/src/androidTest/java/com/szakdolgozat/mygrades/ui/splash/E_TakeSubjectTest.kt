/*
package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.util.TestHelper
import com.szakdolgozat.mygrades.util.TestHelper.clickOnViewChild
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class E_TakeSubjectTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun takeSubjectTest() {


        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

        TestHelper.waitForUI(4)

        TestHelper.logOut()
        TestHelper.waitForUI(2)

        TestHelper.loginTest("pelda@grades.com", "123456")

        TestHelper.waitForUI(4)

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer")
            )
        )
        appCompatImageButton.perform(click())

        TestHelper.waitForUI(1)

        val textView = onView(
            allOf(
                withId(R.id.nav_Email)
            )
        )
        textView.check(matches(withText("pelda@grades.com")))

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

        TestHelper.waitForUI(2)


        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_subject_button)
            )
        )
        floatingActionButton.perform(click())

        TestHelper.waitForUI(2)

        val search= onView(allOf(withId(R.id.new_sub_search))).perform(click())

        TestHelper.waitForUI(1)

        search.perform(replaceText("new"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        onView(withId(R.id.button_search)).perform(click())

        TestHelper.waitForUI(2)

        onView(withId(R.id.add_subject_recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, TestHelper.clickOnViewChild(R.id.take_subject)))

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

        val appCompatImageButton3 = onView(
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
        appCompatImageButton3.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView3 = onView(
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
        navigationMenuItemView3.perform(click())

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
