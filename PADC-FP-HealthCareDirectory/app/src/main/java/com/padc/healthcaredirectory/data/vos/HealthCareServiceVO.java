package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class HealthCareServiceVO {

    public static String IMAGE_URL = "http://www.aungpyaephyo.xyz/healthcare_directory/";

    @SerializedName("health-care-id")
    private int healthCareId;

    @SerializedName("health-care-name")
    private String healthCareName;

    @SerializedName("category")
    private String category;

    @SerializedName("category-mm")
    private String categoryMM;

    @SerializedName("image")
    private String image;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName("website")
    private String website;

    @SerializedName("mapinfo")
    private String mapinfo;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("remark")
    private String remark;

    @SerializedName("phones")
    private ArrayList<PhoneVO> phones;

    @SerializedName("phones")
    private ArrayList<FaxVO> fax;

    @SerializedName("tags")
    private ArrayList<TagVO> tags;

    @SerializedName("operations")
    private ArrayList<OperationVO> operations;

    @SerializedName("price-category")
    private int priceCategory;

    @SerializedName("available-doctors")
    private ArrayList<AvailableDoctorVO> doctors;

    public int getHealthCareId() {
        return healthCareId;
    }

    public void setHealthCareId(int healthCareId) {
        this.healthCareId = healthCareId;
    }

    public String getHealthCareName() {
        return healthCareName;
    }

    public void setHealthCareName(String healthCareName) {
        this.healthCareName = healthCareName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryMM() {
        return categoryMM;
    }

    public void setCategoryMM(String categoryMM) {
        this.categoryMM = categoryMM;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMapinfo() {
        return mapinfo;
    }

    public void setMapinfo(String mapinfo) {
        this.mapinfo = mapinfo;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<PhoneVO> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<PhoneVO> phones) {
        this.phones = phones;
    }

    public ArrayList<FaxVO> getFax() {
        return fax;
    }

    public void setFax(ArrayList<FaxVO> fax) {
        this.fax = fax;
    }

    public ArrayList<TagVO> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagVO> tags) {
        this.tags = tags;
    }

    public ArrayList<OperationVO> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<OperationVO> operations) {
        this.operations = operations;
    }

    public int getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }

    public ArrayList<AvailableDoctorVO> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<AvailableDoctorVO> doctors) {
        this.doctors = doctors;
    }


}
