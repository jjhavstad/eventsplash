package com.eventsplash.eventdetail.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

import com.eventsplash.R
import com.eventsplash.model.eventbright.events.Event
import com.eventsplash.model.eventbright.venues.Venue
import com.eventsplash.providers.FavoritesProvider

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

/**
 * Created by jonathanhavstad on 2/24/18.
 */

class EventWithVenue : View.OnClickListener {
    var event: Event? = null
    var venue: Venue? = null

    override fun onClick(v: View) {
        val context = v.context
        val imageView = v as ImageView
        if (isUserLoggedIn(context)) {
            val c = context.contentResolver.query(FavoritesProvider.CONTENT_URI, null,
                    FavoritesProvider.SEARCH_BY_USER_NAME_AND_EVENT_NAME_SELECTION,
                    arrayOf(getUsername(context) ?: "", event?.name?.text ?: ""), null, null)
            if (c != null) {
                if (0 < c.count) {
                    context.contentResolver.delete(FavoritesProvider.CONTENT_URI,
                            FavoritesProvider.SEARCH_BY_USER_NAME_AND_EVENT_NAME_SELECTION,
                            arrayOf(getUsername(context) ?: "", event?.name?.text ?: "")) // I ❤️ Elvis
                    val favoriteDrawable = ContextCompat.getDrawable(context,
                            R.drawable.ic_favorite_border_white_24dp)
                    imageView.setImageDrawable(favoriteDrawable)
                } else {
                    val contentValues = ContentValues()
                    contentValues.put(FavoritesProvider._USER_NAME, getUsername(context))
                    contentValues.put(FavoritesProvider._EVENT_NAME, event?.name?.text ?: "")
                    context.contentResolver.insert(FavoritesProvider.CONTENT_URI,
                            contentValues)
                    val favoriteDrawable = ContextCompat.getDrawable(context,
                            R.drawable.ic_favorite_white_24dp)
                    imageView.setImageDrawable(favoriteDrawable)
                }
                c.close()
            } else {
                try {
                    val contentValues = ContentValues()
                    contentValues.put(FavoritesProvider._USER_NAME, getUsername(context))
                    contentValues.put(FavoritesProvider._EVENT_NAME, event?.name?.text ?: "")

                    context.contentResolver.insert(FavoritesProvider.CONTENT_URI,
                            contentValues)

                    val favoriteDrawable = ContextCompat.getDrawable(context,
                            R.drawable.ic_favorite_white_24dp)
                    imageView.setImageDrawable(favoriteDrawable)
                } catch (sqlException: SQLException) {

                }

            }
        }
    }

    companion object {
        fun applyFavoriteImage(context: Context,
                               imageView: ImageView,
                               eventWithVenue: EventWithVenue) {
            if (isUserLoggedIn(context)) {
                val c = context.contentResolver.query(FavoritesProvider.CONTENT_URI, null,
                        FavoritesProvider.SEARCH_BY_USER_NAME_AND_EVENT_NAME_SELECTION,
                        arrayOf(getUsername(context) ?: "", eventWithVenue.event?.name?.text ?: ""), null, null)
                if (c != null) {
                    if (0 < c.count) {
                        val favoriteDrawable = ContextCompat.getDrawable(context,
                                R.drawable.ic_favorite_white_24dp)
                        imageView.setImageDrawable(favoriteDrawable)
                    } else {
                        val favoriteDrawable = ContextCompat.getDrawable(context,
                                R.drawable.ic_favorite_border_white_24dp)
                        imageView.setImageDrawable(favoriteDrawable)
                    }
                    c.close()
                } else {
                    val favoriteDrawable = ContextCompat.getDrawable(context,
                            R.drawable.ic_favorite_border_white_24dp)
                    imageView.setImageDrawable(favoriteDrawable)
                }
            } else {
                imageView.setImageDrawable(null)
            }
        }

        private fun isUserLoggedIn(context: Context): Boolean {
            return context.getSharedPreferences(context.getString(R.string.login_status_pref_name), 0)
                    .getBoolean(context.getString(R.string.logged_in_pref_name), false)
        }

        private fun getUsername(context: Context): String? {
            return context.getSharedPreferences(context.getString(R.string.logged_in_username_pref_name), 0)
                    .getString(context.getString(R.string.username_pref_name), null)
        }
    }
}