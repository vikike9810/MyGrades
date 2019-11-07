package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class A_RegisterAndLoginTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)


    @Test
    fun registerAndLoginTest() {

        TestHelper.waitForUI(1)

        val appCompatTextView = onView(
            allOf(
                withText("Create an account"),
                childAtPosition(
                    allOf(
                        withId(R.id.Login_Container),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        TestHelper.waitForUI(1)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.SignUp_EditName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Login_Container),
                        7
                    ),
                    16
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Pelda Jama"))

        TestHelper.waitForUI(1)

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.SignUp_EditEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Login_Container),
                        7
                    ),
                    18
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("pelda@ko.com"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.SignUp_EditPassw),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Login_Container),
                        7
                    ),
                    20
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("1234567"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.SignUp_Teach), withText("Teacher"),
                childAtPosition(
                    allOf(
                        withId(R.id.SignUp_Group),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            21
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView2 = onView(
            allOf(
                withId(R.id.SignUp_OK), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Login_Container),
                        7
                    ),
                    22
                ),
                isDisplayed()
            )
        )
        appCompatTextView2.perform(click())

        TestHelper.loginTest("pelda@ko.com", "1234567")

        TestHelper.waitForUI(3)


        val view = onView(
            allOf(
                withId(R.id.weekView),
                childAtPosition(
                    allOf(
                        withId(R.id.main_fragment),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.View::class.java),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        view.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val view2 = onView(
            allOf(
                withId(R.id.weekView),
                childAtPosition(
                    allOf(
                        withId(R.id.main_fragment),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.View::class.java),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        view2.check(matches(isDisplayed()))
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

        val textView = onView(
            allOf(
                withId(R.id.nav_Name)
            )
        )
        textView.check(matches(withText(equalToIgnoringCase("Pelda Jama"))))

        TestHelper.waitForUI(1)

        val textView2 = onView(
            allOf(
                withId(R.id.nav_Email)
            )
        )
        textView2.check(matches( withText(equalToIgnoringCase("pelda@ko.com"))))
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
