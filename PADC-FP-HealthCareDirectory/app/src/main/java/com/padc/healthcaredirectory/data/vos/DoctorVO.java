package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorVO {

    @SerializedName("id")
    int id;

    @SerializedName("category")
    String category;

    @SerializedName("category_mm")
    String categoryMM;

    @SerializedName("name")
    String name;

    @SerializedName("qualifications")
    String qualifications;

    @SerializedName("image")
    String image;

    @SerializedName("email")
    String email;

    @SerializedName("facebook")
    String facebook;

    @SerializedName("website")
    String website;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategoryMM(String categoryMM) {
        this.categoryMM = categoryMM;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getWebsite() {
        return website;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryMM() {
        return categoryMM;
    }
}
