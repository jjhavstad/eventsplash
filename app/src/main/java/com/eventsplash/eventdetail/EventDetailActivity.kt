package com.eventsplash.eventdetail

import android.content.Intent
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem

import com.eventsplash.R
import com.eventsplash.databinding.ActivityDetailBinding
import com.eventsplash.eventdetail.models.EventWithVenue
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil

/**
 * Created by jonathanhavstad on 2/28/18.
 */

class EventDetailActivity : AppCompatActivity() {
    private var eventWithVenue: EventWithVenue? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent

        val args = intent.getBundleExtra(getString(R.string.event_detail_bundle_key))
        val eventDetailBinder = args.getBinder(getString(R.string.event_detail_binder_key)) as EventDetailBinder
        if (eventDetailBinder != null) {
            eventWithVenue = eventDetailBinder.eventWithVenue
        }

        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.eventWithVenue = eventWithVenue

        val toolbar = binding.root.findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = eventWithVenue!!.event!!.name.text
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
