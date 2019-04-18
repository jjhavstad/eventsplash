package com.eventsplash;

import com.eventsplash.base.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

/**
 * Created by jonathanhavstad on 2/25/18.
 */

public class MainActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void validateFragmentPlaceholders() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.main_content))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
