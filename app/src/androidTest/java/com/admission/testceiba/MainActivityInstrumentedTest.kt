package com.admission.testceiba

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.admission.testceiba.CustomMatchers.Companion.withItemCount
import com.admission.testceiba.ui.users.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun findUser() {
        onView(withId(R.id.edtSearchUser)).perform(typeText("leanne"), ViewActions.closeSoftKeyboard())
        onView(withText("leanne")).check(matches(isDisplayed()));
    }

    @Test
    fun filterUsersIsEmpty() {
        onView(withId(R.id.edtSearchUser)).perform(typeText("abcdfghijk-123456789"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tvNotFoundResults)).check(matches(isDisplayed()));
    }

    @Test
    fun loadUsers() {
        onView(withId(R.id.recyclerViewUsers)).check(matches(withItemCount(10)))
    }

    @Test
    fun viewPost() {
        onView(withId(R.id.recyclerViewUsers)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>
                (0, ClickOnButtonView()))
        onView(withId(R.id.tvName)).check(matches(isDisplayed()));
        onView(withId(R.id.tvEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.tvPhone)).check(matches(isDisplayed()));
    }
}