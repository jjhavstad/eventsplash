
package com.eventsplash.model.eventbright.events;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

public class Original {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Original) {
            Original original = (Original) obj;
            return ((url == null && original.url == null) || (url != null && url.equals(original.url))) &&
                    ((width == null && original.width == null) || (width != null && width.equals(original.width))) &&
                    ((height == null && original.height == null) || (height != null && height.equals(original.height)));
        }
        return super.equals(obj);
    }
}
