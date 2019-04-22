
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logo {

    @SerializedName("crop_mask")
    @Expose
    private CropMask cropMask;
    @SerializedName("original")
    @Expose
    private Original original;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("aspect_ratio")
    @Expose
    private String aspectRatio;
    @SerializedName("edge_color")
    @Expose
    private String edgeColor;
    @SerializedName("edge_color_set")
    @Expose
    private Boolean edgeColorSet;

    public CropMask getCropMask() {
        return cropMask;
    }

    public void setCropMask(CropMask cropMask) {
        this.cropMask = cropMask;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(String edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Boolean getEdgeColorSet() {
        return edgeColorSet;
    }

    public void setEdgeColorSet(Boolean edgeColorSet) {
        this.edgeColorSet = edgeColorSet;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Logo) {
            Logo logo = (Logo) obj;
            return ((cropMask == null && logo.cropMask == null) || (cropMask != null && cropMask.equals(logo.cropMask))) &&
                    ((original == null && logo.original == null) || (original != null && original.equals(logo.original))) &&
                    ((id == null && logo.id == null) || (id != null && id.equals(logo.id))) &&
                    ((url == null && logo.url == null) || (url != null && url.equals(logo.url))) &&
                    ((aspectRatio == null && logo.aspectRatio == null) || (aspectRatio != null && aspectRatio.equals(logo.aspectRatio))) &&
                    ((edgeColor == null && logo.edgeColor == null) || (edgeColor != null && edgeColor.equals(logo.edgeColor))) &&
                    ((edgeColorSet == null && logo.edgeColorSet == null) || (edgeColorSet != null && edgeColorSet.equals(logo.edgeColorSet)));
        }
        return super.equals(obj);
    }
}
