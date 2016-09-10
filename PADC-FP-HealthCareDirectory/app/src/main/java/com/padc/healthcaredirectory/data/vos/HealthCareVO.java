package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("address")
    private String address;

    @SerializedName("township")
    private String township;

    @SerializedName("phones")
    private String[] phones;

    @SerializedName("email")
    private String email;

    @SerializedName("website")
    private String website;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("mapinfo")
    private String mapinfo;

    @SerializedName("images")
    private String[] images;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getTownship() {
        return township;
    }

    public String[] getPhones() {
        return phones;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getMapinfo() {
        return mapinfo;
    }

    public String[] getImages() {
        return images;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setMapinfo(String mapinfo) {
        this.mapinfo = mapinfo;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
