package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/14/2016.
 */
public class ArticleVO {

    @SerializedName("id")
    long id;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("image")
    String image;

    @SerializedName("article_date")
    String articleDate;

    @SerializedName("author")
    String author;

    @SerializedName("website")
    String website;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getWebsite() {
        return website;
    }
}
