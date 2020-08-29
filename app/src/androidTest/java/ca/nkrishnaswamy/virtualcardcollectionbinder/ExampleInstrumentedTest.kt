package ca.nkrishnaswamy.virtualcardcollectionbinder

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import ca.nkrishnaswamy.virtualcardcollectionbinder.ui.activities.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule @JvmField var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ca.nkrishnaswamy.virtualcardcollectionbinder", appContext.packageName)
    }

    @Test
    fun checkToastAfterNotEnteringName(){
        onView(withId(R.id.add_card_button)).perform(click())
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withText(R.string.errorEmptyStrings)).inRoot(ToastMatcher().apply { matches(
            withText(R.string.errorEmptyStrings)) })
    }

    @Test
    fun checkToastAfterEnteringWrongDetails(){
        onView(withId(R.id.add_card_button)).perform(click())
        onView(withId(R.id.enterCardName)).perform(typeText("testing"), closeSoftKeyboard())
        onView(withId(R.id.buttonSearch)).perform(click())
        onView(withText(R.string.errorCardNotFound)).inRoot(ToastMatcher().apply { matches(
            withText(R.string.errorCardNotFound)) })
    }
}