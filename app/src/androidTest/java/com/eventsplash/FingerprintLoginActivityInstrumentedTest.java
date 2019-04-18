package com.eventsplash;

import com.eventsplash.login.FingerprintLoginActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

/**
 * Created by jonathanhavstad on 2/28/18.
 */

public class FingerprintLoginActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<FingerprintLoginActivity> mActivityRule = new ActivityTestRule<>(
            FingerprintLoginActivity.class);

    @Test
    public void validateFragmentPlaceholders() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.fingerprint_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
