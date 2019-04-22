
package com.eventsplash.model.eventbright.venues;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("age_restriction")
    @Expose
    private Object ageRestriction;
    @SerializedName("capacity")
    @Expose
    private Object capacity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Object ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Object getCapacity() {
        return capacity;
    }

    public void setCapacity(Object capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Venue) {
            Venue venue = (Venue) obj;
            return ((address == null && venue.address == null) || (address != null && address.equals(venue.address))) &&
                    ((resourceUri == null && venue.resourceUri == null) || (resourceUri != null && resourceUri.equals(venue.resourceUri))) &&
                    ((id == null && venue.id == null) || (id != null && id.equals(venue.id))) &&
                    ((name == null && venue.name == null) || (name != null && name.equals(venue.name))) &&
                    ((latitude == null && venue.latitude == null) || (latitude != null && latitude.equals(venue.latitude))) &&
                    ((longitude == null && venue.longitude == null) || (longitude != null && longitude.equals(venue.longitude)));
        }
        return super.equals(obj);
    }
}
