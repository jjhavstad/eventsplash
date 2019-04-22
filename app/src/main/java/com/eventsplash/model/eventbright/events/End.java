
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class End {

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
        if (obj instanceof End) {
            End end = (End) obj;
            return ((timezone == null && end.timezone == null) || (timezone != null && timezone.equals(end.timezone))) &&
                    ((local == null && end.local == null) || (local != null && local.equals(end.local))) &&
                    ((utc == null && end.utc == null) || (utc != null && utc.equals(end.utc)));
        }
        return super.equals(obj);
    }
}
