package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/21/2016.
 */
public class HealthCareInfoVO {

    public static String IMAGE_URL = "http://www.aungpyaephyo.xyz/healthcare_directory/";

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    /**
    @SerializedName("author")
    private ArrayList<AuthorVO> author;
    /**/

    @SerializedName("short-description")
    private String shortDescription;

    @SerializedName("published-date")
    private String publishedDate;

    @SerializedName("complete-url")
    private String completeUrl;

    @SerializedName("info-type")
    private String infoType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
    public ArrayList<AuthorVO> getAuthor() {
        return author;
    }
    /**/

    /**
    public void setAuthor(ArrayList<AuthorVO> author) {
        this.author = author;
    }
    /**/

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}
