package com.eventsplash.eventdetail.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("favoriteImage")
fun ImageView.bindWithEventVenue(eventWithVenue: EventWithVenue) {
    EventWithVenue.applyFavoriteImage(this.context,
            this,
            eventWithVenue)
}