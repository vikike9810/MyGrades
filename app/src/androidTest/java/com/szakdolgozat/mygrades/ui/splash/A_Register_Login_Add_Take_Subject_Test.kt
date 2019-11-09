package com.szakdolgozat.mygrades.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
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
class A_Register_Login_Add_Take_Subject_Test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)


    @Test
    fun A_registerAndLoginTest() {

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

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
        textView2.check(matches(withText(equalToIgnoringCase("pelda@ko.com"))))
        TestHelper.waitForUI(1)


        //---------------------------------------------------------------------------------------------------------------------
        //AddSubject


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

        val appCompatTextView3 = Espresso.onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(1)
        appCompatTextView3.perform(click())

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


        //---------------------------------------------------------------------------------------------------------------------
        //TakeSubject

        TestHelper.logOut()
        TestHelper.waitForUI(2)

        TestHelper.loginTest("pelda@grades.com", "123456")

        TestHelper.waitForUI(4)

        val appCompatImageButton6 = onView(
            allOf(
                withContentDescription("Open navigation drawer")
            )
        )
        appCompatImageButton6.perform(click())

        TestHelper.waitForUI(1)

        val textView4 = onView(
            allOf(
                withId(R.id.nav_Email)
            )
        )
        textView4.check(matches(withText("pelda@grades.com")))

        TestHelper.waitForUI(1)

        val navigationMenuItemView6 = onView(
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
        navigationMenuItemView6.perform(click())

        TestHelper.waitForUI(2)


        val floatingActionButton5 = onView(
            allOf(
                withId(R.id.add_subject_button)
            )
        )
        floatingActionButton5.perform(click())

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

        val appCompatImageButton5 = onView(
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
        appCompatImageButton5.perform(click())

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

        val linearLayout4 = onView(
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
        linearLayout4.check(matches(isDisplayed()))

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





