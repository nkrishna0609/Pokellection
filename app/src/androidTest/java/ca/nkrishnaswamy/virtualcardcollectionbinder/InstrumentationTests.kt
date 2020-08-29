package ca.nkrishnaswamy.virtualcardcollectionbinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import ca.nkrishnaswamy.virtualcardcollectionbinder.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class InstrumentationTests {

    @Rule
    @JvmField var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java)

    @Test
    fun checkToastAfterNotEnteringNameTest(){
        Espresso.onView(ViewMatchers.withId(R.id.add_card_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonSearch)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.errorEmptyStrings)).inRoot(ToastMatcher().apply { matches(
            ViewMatchers.withText(R.string.errorEmptyStrings)
        ) })
    }

    @Test
    fun checkToastAfterEnteringWrongDetailsTest(){
        Espresso.onView(ViewMatchers.withId(R.id.add_card_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.enterCardName))
            .perform(ViewActions.typeText("testing"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.buttonSearch)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText(R.string.errorCardNotFound)).inRoot(ToastMatcher().apply { matches(
            ViewMatchers.withText(R.string.errorCardNotFound)
        ) })
    }
}