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
class F_Add_Check_Grade_Messaging_Test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun addGradeTest() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()

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

        //---------------------------------------------------------------------------------
        //EditProfil
        TestHelper.waitForUI(4)

        val appCompatImageButton4 = onView(
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
        appCompatImageButton4.perform(click())

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
                    1
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView3.perform(click())

        TestHelper.waitForUI(1)


        TestHelper.setDate(R.id.Profil_Bday,1990, 11,5)

        TestHelper.waitForUI(1)

        val appCompatTextView8 = onView(
            allOf(
                withId(R.id.Profil_city))
        )
        appCompatTextView8.perform(click())

        TestHelper.waitForUI(1)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.Profil_Editcity)
            )
        )
        appCompatEditText.perform(replaceText("New York"))

        TestHelper.waitForUI(1)


        val appCompatTextView9 = onView(
            allOf(
                withId(R.id.Profil_Zip)
            )
        )
        appCompatTextView9.perform(click())

        TestHelper.waitForUI(1)

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.Profil_EditZip)
            )
        )
        appCompatEditText5.perform(replaceText("1105"))

        TestHelper.waitForUI(1)


        val floatingActionButton3 = onView(
            allOf(
                withId(R.id.floatingActionButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_fragment),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton3.perform(click())

        TestHelper.waitForUI(2)

        val textView4 = onView(
            allOf(
                withId(R.id.Profil_Bday), withText("1990.11.5"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.Profil_profilCard),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("1990.11.5")))

        TestHelper.waitForUI(1)


        val textView5 = onView(
            allOf(
                withId(R.id.Profil_city), withText("New York"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView2),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("New York")))

        TestHelper.waitForUI(1)

        //-------------------------------------------
        //Grades

        TestHelper.waitForUI(4)

        TestHelper.logOut()

        TestHelper.waitForUI(1)

        TestHelper.loginTest("pelda@grades.com", "123456")

        TestHelper.waitForUI(1)

        val appCompatImageButton10 = onView(
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
        appCompatImageButton10.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView7 = onView(
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
        navigationMenuItemView7.perform(click())

        TestHelper.waitForUI(1)

        val frameLayout3 = onView(
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
        frameLayout3.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val textView6 = onView(
            allOf(
                withId(R.id.grade_teacher), withText("Pelda Jama")
            )
        )
        textView6.check(matches(withText("Pelda Jama")))

        TestHelper.waitForUI(1)

        val textView7 = onView(
            allOf(
                withId(R.id.grade_subject), withText("new")
            )
        )
        textView7.check(matches(withText("new")))

        TestHelper.waitForUI(1)

        val appCompatImageView10 = onView(
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
        appCompatImageView10.perform(click())

        TestHelper.waitForUI(1)

        val appCompatSpinner10 = onView(
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
        appCompatSpinner10.perform(click())

        TestHelper.waitForUI(1)

        val appCompatTextView10 = onData(anything())
            .inAdapterView(withClassName(`is`("androidx.appcompat.widget.DropDownListView")))
            .atPosition(1)
        appCompatTextView10.perform(click())

        TestHelper.waitForUI(1)

        val appCompatButton11 = onView(
            allOf(
                withId(R.id.grade_go_button)
            )
        )
        appCompatButton11.perform(click())

        TestHelper.waitForUI(1)

        val frameLayout12 = onView(
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
        frameLayout12.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val appCompatImageButton13 = onView(
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
        appCompatImageButton13.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView210 = onView(
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
        navigationMenuItemView210.perform(click())

        TestHelper.waitForUI(1)
//----------------------------------------------------------------------------------------

        TestHelper.waitForUI(5)

        TestHelper.logOut()

        TestHelper.waitForUI(1)

        TestHelper.loginTest("pelda@ko.com", "1234567")

        TestHelper.waitForUI(4)

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer")
            )
        )
        appCompatImageButton.perform(click())

        TestHelper.waitForUI(1)

        val navigationMenuItemView10 = onView(
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
        navigationMenuItemView10.perform(click())

        TestHelper.waitForUI(2)

        val floatingActionButton10 = onView(
            allOf(
                withId(R.id.add_new_talk)
            )
        )
        floatingActionButton10.perform(click())

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
                    0
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

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.talking_editText)
            )
        )
        appCompatEditText10.perform(replaceText("Hi! You can take my subject!"), closeSoftKeyboard())

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

        val textView10 = onView(
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
        textView10.check(matches(withText("Hi! You can take my subject!")))
        TestHelper.waitForUI(1)

        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()


        TestHelper.waitForUI(4)


        //Logout
        TestHelper.logOut()

        TestHelper.waitForUI(1)

        //Login
        TestHelper.loginTest("pelda@grades.com", "123456")

        val appCompatImageButton20 = onView(
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
        appCompatImageButton20.perform(click())

        TestHelper.waitForUI(5)

        val navigationMenuItemView22 = onView(
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
        navigationMenuItemView22.perform(click())

        TestHelper.waitForUI(1)

        val cardView3 = onView(
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
        cardView3.check(matches(isDisplayed()))
        TestHelper.waitForUI(1)
        cardView3.perform(scrollTo(), click())

        TestHelper.waitForUI(1)

        val linearLayout3 = onView(
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
        linearLayout3.check(matches(isDisplayed()))

        TestHelper.waitForUI(1)

        val textView50 = onView(
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
        textView50.check(matches(withText("Hi! You can take my subject!")))

        TestHelper.waitForUI(1)

        val appCompatEditText30 = onView(
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
        appCompatEditText30.perform(replaceText("Hi, ok"), closeSoftKeyboard())

        TestHelper.waitForUI(1)

        val appCompatImageView30 = onView(
            allOf(
                withId(R.id.send_button)
            )
        )
        appCompatImageView30.perform(click())

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

        val textView24 = onView(
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
        textView24.check(matches(withText("Hi, ok")))



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
