package com.admission.testceiba

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import org.hamcrest.Matcher

class ClickOnButtonView : ViewAction {
    var click: ViewAction = ViewActions.click()

    override fun getConstraints(): Matcher<View> {
        return click.constraints
    }

    override fun getDescription(): String {
        return " click on custom button view"
    }

    override fun perform(uiController: UiController, view: View) {
        //btnClickMe -> Custom row item view button
        click.perform(uiController, view.findViewById(R.id.btnViewPosts))
    }
}