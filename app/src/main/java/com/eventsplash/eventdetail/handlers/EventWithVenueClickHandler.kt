package com.eventsplash.eventdetail.handlers

import android.view.View

import com.eventsplash.eventdetail.models.EventWithVenue

/**
 * Created by jonathanhavstad on 2/28/18.
 */

class EventWithVenueClickHandler(val onSelectEventWithVenue: OnSelectEventWithVenue) : View.OnClickListener {

    var eventWithVenue: EventWithVenue? = null

    interface OnSelectEventWithVenue {
        fun selectEventWithVenue(eventWithVenue: EventWithVenue?)
    }

    override fun onClick(v: View) {
        onSelectEventWithVenue.selectEventWithVenue(eventWithVenue)
    }
}
