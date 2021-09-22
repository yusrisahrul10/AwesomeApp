package engineer.yusrisahrul.awesomeapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import engineer.yusrisahrul.awesomeapp.R
import engineer.yusrisahrul.awesomeapp.utils.EspressoIdlingResource
import engineer.yusrisahrul.awesomeapp.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.annotation.Config

@HiltAndroidTest
class PhotoActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule = ActivityScenarioRule(PhotoActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadPhotos() {
        Espresso.onView(withId(R.id.rvPhoto))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvPhoto))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
        Espresso.onView(withId(R.id.rvPhoto))
            .check(RecyclerViewItemCountAssertion(15))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}