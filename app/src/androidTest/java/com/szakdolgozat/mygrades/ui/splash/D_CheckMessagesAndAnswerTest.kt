package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class D_CheckMessagesAndAnswerTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun checkMessagesEndAnswerTest() {

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

        TestHelper.waitForUI(4)

        //Logout
        TestHelper.logOut()

        TestHelper.waitForUI(1)

        //Login
        TestHelper.loginTest("pelda@grades.com", "123456")

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
                    5
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        TestHelper.waitForUI(1)

        val cardView = onView(
            childAtPosition(
                allOf(
                    withId(R.id.talking_recycler),
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    )
                ),
                0
            )
        )
        cardView.check(matches(isDisplayed()))
        TestHelper.waitForUI(1)
        cardView.perform(scrollTo(), click())

        TestHelper.waitForUI(1)

        val linearLayout = onView(
            allOf(
                withId(R.id.message_layout),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.messages_recycler),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val textView = onView(
            allOf(
                withId(R.id.message_text), withText("Hi! You can take my subject!"),
                childAtPosition(
                    allOf(
                        withId(R.id.message_layout),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Hi! You can take my subject!")))

        TestHelper.waitForUI(1)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.talking_editText),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Hi, ok"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.send_button)
            )
        )
        appCompatImageView.perform(click())

        TestHelper.waitForUI(1)

        val linearLayout2 = onView(
            allOf(
                withId(R.id.message_layout),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.messages_recycler),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout2.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val textView2 = onView(
            allOf(
                withId(R.id.message_text), withText("Hi, ok"),
                childAtPosition(
                    allOf(
                        withId(R.id.message_layout),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Hi, ok")))



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
