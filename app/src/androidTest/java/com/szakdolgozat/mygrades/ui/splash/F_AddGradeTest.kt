package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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
class F_AddGradeTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun addGradeTest() {

        TestHelper.waitForUI(4)

        TestHelper.logOut()

        TestHelper.loginTest("pelda@ko.com","1234567")

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

        val floatingActionButton = onView(
            allOf(
                withId(R.id.grade_add)
            )
        )
        floatingActionButton.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.add_grade_subject_spinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
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

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.add_grade_student_spinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatSpinner2.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView2 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(0)
        appCompatTextView2.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner3 = onView(
            allOf(
                withId(R.id.add_grade_spinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatSpinner3.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView3 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(4)
        appCompatTextView3.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.grade_comment)
            )
        )
        textInputEditText.perform(replaceText("Not Bad"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatButton = onView(
            allOf(
                withId(R.id.add_new_grade)
            )
        )
        appCompatButton.perform(click())

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
                withId(R.id.grade_subject), withText("new"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("new")))


        val textView2 = onView(
            allOf(
                withId(R.id.grade_teacher), withText("Csillag Patrik")
            )
        )
        textView2.check(matches(withText("Csillag Patrik")))

        val textView3 = onView(
            allOf(
                withId(R.id.grade_grade), withText("4")
            )
        )
        textView3.check(matches(withText("4")))

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.Filter_arrow)
            )
        )
        appCompatImageView2.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner4 = onView(
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
        appCompatSpinner4.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView4 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(1)
        appCompatTextView4.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner5 = onView(
            allOf(
                withId(R.id.Grade_spinner),
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
        appCompatSpinner5.perform(click())

        TestHelper.waitForUI(1)


        val appCompatTextView5 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(2)
        appCompatTextView5.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.grade_go_button), withText("Go")
            )
        )
        appCompatButton2.perform(click())

        val frameLayout4 = onView(
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
        frameLayout4.check(doesNotExist())

        TestHelper.waitForUI(1)

        val appCompatSpinner6 = onView(
            allOf(
                withId(R.id.Grade_spinner),
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
        appCompatSpinner6.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView6 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(4)
        appCompatTextView6.perform(click())

        TestHelper.waitForUI(1)

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.grade_go_button), withText("Go")
            )
        )
        appCompatButton3.perform(click())

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
        frameLayout2.check(matches(isDisplayed()))


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
