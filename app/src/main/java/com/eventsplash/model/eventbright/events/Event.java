
package com.eventsplash.model.eventbright.events;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("vanity_url")
    @Expose
    private String vanityUrl;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("capacity_is_custom")
    @Expose
    private Boolean capacityIsCustom;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("listed")
    @Expose
    private Boolean listed;
    @SerializedName("shareable")
    @Expose
    private Boolean shareable;
    @SerializedName("online_event")
    @Expose
    private Boolean onlineEvent;
    @SerializedName("tx_time_limit")
    @Expose
    private Integer txTimeLimit;
    @SerializedName("hide_start_date")
    @Expose
    private Boolean hideStartDate;
    @SerializedName("hide_end_date")
    @Expose
    private Boolean hideEndDate;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("is_locked")
    @Expose
    private Boolean isLocked;
    @SerializedName("privacy_setting")
    @Expose
    private String privacySetting;
    @SerializedName("is_series")
    @Expose
    private Boolean isSeries;
    @SerializedName("is_series_parent")
    @Expose
    private Boolean isSeriesParent;
    @SerializedName("is_reserved_seating")
    @Expose
    private Boolean isReservedSeating;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("is_free")
    @Expose
    private Boolean isFree;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("logo_id")
    @Expose
    private String logoId;
    @SerializedName("organizer_id")
    @Expose
    private String organizerId;
    @SerializedName("venue_id")
    @Expose
    private String venueId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("subcategory_id")
    @Expose
    private String subcategoryId;
    @SerializedName("format_id")
    @Expose
    private String formatId;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("logo")
    @Expose
    private Logo logo;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
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

    public String getVanityUrl() {
        return vanityUrl;
    }

    public void setVanityUrl(String vanityUrl) {
        this.vanityUrl = vanityUrl;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getCapacityIsCustom() {
        return capacityIsCustom;
    }

    public void setCapacityIsCustom(Boolean capacityIsCustom) {
        this.capacityIsCustom = capacityIsCustom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getListed() {
        return listed;
    }

    public void setListed(Boolean listed) {
        this.listed = listed;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(Boolean shareable) {
        this.shareable = shareable;
    }

    public Boolean getOnlineEvent() {
        return onlineEvent;
    }

    public void setOnlineEvent(Boolean onlineEvent) {
        this.onlineEvent = onlineEvent;
    }

    public Integer getTxTimeLimit() {
        return txTimeLimit;
    }

    public void setTxTimeLimit(Integer txTimeLimit) {
        this.txTimeLimit = txTimeLimit;
    }

    public Boolean getHideStartDate() {
        return hideStartDate;
    }

    public void setHideStartDate(Boolean hideStartDate) {
        this.hideStartDate = hideStartDate;
    }

    public Boolean getHideEndDate() {
        return hideEndDate;
    }

    public void setHideEndDate(Boolean hideEndDate) {
        this.hideEndDate = hideEndDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getPrivacySetting() {
        return privacySetting;
    }

    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }

    public Boolean getIsSeries() {
        return isSeries;
    }

    public void setIsSeries(Boolean isSeries) {
        this.isSeries = isSeries;
    }

    public Boolean getIsSeriesParent() {
        return isSeriesParent;
    }

    public void setIsSeriesParent(Boolean isSeriesParent) {
        this.isSeriesParent = isSeriesParent;
    }

    public Boolean getIsReservedSeating() {
        return isReservedSeating;
    }

    public void setIsReservedSeating(Boolean isReservedSeating) {
        this.isReservedSeating = isReservedSeating;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLogoId() {
        return logoId;
    }

    public void setLogoId(String logoId) {
        this.logoId = logoId;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return ((name == null && event.name == null) || name != null && name.equals(event.name)) &&
                    ((description == null && event.description == null) || (description != null && description.equals(event.description))) &&
                    ((id == null && event.id == null) || (id != null && id.equals(event.id))) &&
                    ((url == null && event.url == null) || (url != null && url.equals(event.url))) &&
                    ((vanityUrl == null && event.vanityUrl == null) || (vanityUrl != null && vanityUrl.equals(event.vanityUrl))) &&
                    ((start == null && event.start == null) || (start != null && start.equals(event.start))) &&
                    ((end == null && event.end == null) || (end != null && end.equals(event.end))) &&
                    ((organizationId == null && event.organizationId == null) || (organizationId != null && organizationId.equals(event.organizationId))) &&
                    ((created == null && event.created == null) || (created != null && created.equals(event.created))) &&
                    ((changed == null && event.changed == null) || (changed != null && changed.equals(event.changed))) &&
                    ((capacity == null && event.capacity == null) || (capacity != null && capacity.equals(event.capacity))) &&
                    ((capacityIsCustom == null && event.capacityIsCustom == null) || (capacityIsCustom != null && capacityIsCustom.equals(event.capacityIsCustom))) &&
                    ((status == null && event.status == null) || (status != null && status.equals(event.status))) &&
                    ((currency == null && event.currency == null) || (currency != null && currency.equals(event.currency))) &&
                    ((listed == null && event.listed == null) || (listed != null && listed.equals(event.listed))) &&
                    ((shareable == null && event.shareable == null) || (shareable != null && shareable.equals(event.shareable))) &&
                    ((onlineEvent == null && event.onlineEvent == null) || (onlineEvent != null && onlineEvent.equals(event.onlineEvent))) &&
                    ((txTimeLimit == null && event.txTimeLimit == null) || (txTimeLimit != null && txTimeLimit.equals(event.txTimeLimit))) &&
                    ((hideStartDate == null && event.hideStartDate == null) || (hideStartDate != null && hideStartDate.equals(event.hideStartDate))) &&
                    ((hideEndDate == null && event.hideEndDate == null) || (hideEndDate != null && hideEndDate.equals(event.hideEndDate))) &&
                    ((locale == null && event.locale == null) || (locale != null && locale.equals(event.locale))) &&
                    ((isLocked == null && event.isLocked == null) || (isLocked != null && isLocked.equals(event.isLocked))) &&
                    ((privacySetting == null && event.privacySetting == null) || (privacySetting != null && privacySetting.equals(event.privacySetting))) &&
                    ((isSeries == null && event.isSeries == null) || (isSeries != null && isSeries.equals(event.isSeries))) &&
                    ((isSeriesParent == null && event.isSeriesParent == null) || (isSeriesParent != null && isSeriesParent.equals(event.isSeriesParent))) &&
                    ((isReservedSeating == null && event.isReservedSeating == null) || (isReservedSeating != null && isReservedSeating.equals(event.isReservedSeating))) &&
                    ((source == null && event.source == null) || (source != null && source.equals(event.source))) &&
                    ((isFree == null && event.isFree == null) || (isFree != null && isFree.equals(event.isFree))) &&
                    ((version == null && event.version == null) || (version != null && version.equals(event.version))) &&
                    ((logoId == null && event.logoId == null) || (logoId != null && logoId.equals(event.logoId))) &&
                    ((organizerId == null && event.organizerId == null) || (organizerId != null && organizerId.equals(event.organizerId))) &&
                    ((venueId == null && event.venueId == null) || (venueId != null && venueId.equals(event.venueId))) &&
                    ((categoryId == null && event.categoryId == null) || (categoryId != null && categoryId.equals(event.categoryId))) &&
                    ((subcategoryId == null && event.subcategoryId == null) || (subcategoryId != null && subcategoryId.equals(event.subcategoryId))) &&
                    ((formatId == null && event.formatId == null) || (formatId != null && formatId.equals(event.formatId))) &&
                    ((resourceUri == null && event.resourceUri == null) || (resourceUri != null && resourceUri.equals(event.resourceUri))) &&
                    ((logo == null && event.logo == null) || (logo != null && logo.equals(event.logo)));
        }
        return super.equals(obj);
    }
}
