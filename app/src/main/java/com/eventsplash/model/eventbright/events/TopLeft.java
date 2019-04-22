
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopLeft {

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TopLeft) {
            TopLeft topLeft = (TopLeft) obj;
            return ((x == null && topLeft.x == null) || (x != null && x.equals(topLeft.x))) &&
                    ((y == null && topLeft.y == null) || (y != null && y.equals(topLeft.y)));
        }
        return super.equals(obj);
    }
}
