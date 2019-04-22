
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("html")
    @Expose
    private String html;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Name) {
            Name name = (Name) obj;
            return ((text == null && name.text == null) || (text != null && text.equals(name.text))) &&
                    ((html == null && name.html == null) || (html != null && html.equals(name.html)));
        }
        return super.equals(obj);
    }
}
