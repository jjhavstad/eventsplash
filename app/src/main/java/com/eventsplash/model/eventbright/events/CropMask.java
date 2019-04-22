
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropMask {

    @SerializedName("top_left")
    @Expose
    private TopLeft topLeft;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public TopLeft getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(TopLeft topLeft) {
        this.topLeft = topLeft;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CropMask) {
            CropMask cropMask = (CropMask) obj;
            return ((topLeft == null && cropMask.topLeft == null) || (topLeft != null && topLeft.equals(cropMask.topLeft))) &&
                    ((width == null && cropMask.width == null) || (width != null && width.equals(cropMask.width))) &&
                    ((height == null && cropMask.height == null) || (height != null && height.equals(cropMask.height)));
        }
        return super.equals(obj);
    }
}
