package com.eventsplash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.eventsplash.eventdetail.EventDetailActivity;
import com.eventsplash.eventdetail.EventDetailBinder;
import com.eventsplash.eventdetail.models.EventWithVenue;
import com.eventsplash.model.eventbright.events.Event;
import com.eventsplash.model.eventbright.events.Name;
import com.eventsplash.model.eventbright.venues.Venue;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

/**
 * Created by jonathanhavstad on 3/2/18.
 */

public class EventDetailActivityInstrumentedTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<EventDetailActivity>(
            EventDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            EventWithVenue eventWithVenue = new EventWithVenue();
            Event event = new Event();
            Name name = new Name();
            name.setText("Test Event");
            event.setName(name);
            eventWithVenue.setEvent(event);
            Venue venue = new Venue();
            eventWithVenue.setVenue(venue);
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, EventDetailActivity.class);
            Bundle args = new Bundle();
            args.putBinder("event_detail_binder",
                    new EventDetailBinder(eventWithVenue));
            result.putExtra("event_detail_bundle", args);
            return result;
        }
    };

    @Test
    public void validateFragmentPlaceholders() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.event_detail_content))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
