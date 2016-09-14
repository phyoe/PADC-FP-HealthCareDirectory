package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareVO {

    public static String IMAGE_URL = "http://www.aungpyaephyo.xyz/healthcare_directory/";

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("category_mm")
    private String category_mm;

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
    private String[] stockPhotoPath;

    private int photo_count;

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
        return stockPhotoPath;
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
        this.stockPhotoPath = images;
    }

    public String getCategory_mm() {
        return category_mm;
    }

    public void setCategory_mm(String category_mm) {
        this.category_mm = category_mm;
    }

    public HealthCareVO(){
        super();
    }

    public HealthCareVO(int id, String name, String address, String[] phones, String[] stockPhotoPath) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.stockPhotoPath = stockPhotoPath;
        this.photo_count = stockPhotoPath.length;
    }

    public String[] getStockPhotoPath() {
        for (int i = 0; i < getPhoto_count(); i++){
            stockPhotoPath[i] = IMAGE_URL + stockPhotoPath[i];
        }
        return stockPhotoPath;
    }

    public int getPhoto_count() {
        return photo_count;
    }
}
