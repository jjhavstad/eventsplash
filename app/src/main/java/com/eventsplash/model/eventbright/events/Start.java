
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start {

    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("utc")
    @Expose
    private String utc;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Start) {
            Start start = (Start) obj;
            return ((timezone == null && start.timezone == null) || (timezone != null && timezone.equals(start.timezone))) &&
                    ((local == null && start.local == null) || (local != null && local.equals(start.local))) &&
                    ((utc == null && start.utc == null) || (utc != null && utc.equals(start.utc)));
        }
        return super.equals(obj);
    }
}
