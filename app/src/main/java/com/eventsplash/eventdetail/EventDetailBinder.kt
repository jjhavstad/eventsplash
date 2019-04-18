package com.eventsplash.eventdetail

import android.os.Binder

import com.eventsplash.eventdetail.models.EventWithVenue

/**
 * Created by jonathanhavstad on 2/28/18.
 */

class EventDetailBinder(var eventWithVenue: EventWithVenue?) : Binder()
