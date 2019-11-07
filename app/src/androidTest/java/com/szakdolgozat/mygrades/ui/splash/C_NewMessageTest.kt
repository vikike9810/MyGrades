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
class C_NewMessageTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun newMessageTest() {

        TestHelper.waitForUI(5)

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer")
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

        TestHelper.waitForUI(2)

        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_new_talk)
            )
        )
        floatingActionButton.perform(click())

        TestHelper.waitForUI(1)

        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.new_talking_recycler),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        TestHelper.waitForUI(1)

        val cardView2 = onView(
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
        cardView2.perform(scrollTo(), click())

        TestHelper.waitForUI(1)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.talking_editText)
            )
        )
        appCompatEditText.perform(replaceText("Hi! You can take my subject!"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.send_button)
            )
        )
        appCompatImageView.perform(click())


        TestHelper.waitForUI(2)


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

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()


        TestHelper.waitForUI(4)





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
