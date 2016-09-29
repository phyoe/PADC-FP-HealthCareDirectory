package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saw Yu Nwe on 9/28/2016.
 */
public class DiseaseVO {

    /* name:"xxx",
        description:"xxx",                                        image:"xxx.jpg",
        refs : ["xxx", "xxx", "xxx"],
        hospital_list:[
            {
                id:1,
                name:"Hospital-A"
            },
        ],

     */

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("references")
    String references;

    @SerializedName("author")
    String author;

    @SerializedName("website")
    String website;

    @SerializedName("image")
    String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReferences() {
        return references;
    }

    public String getAuthor() {
        return author;
    }

    public String getWebsite() {
        return website;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
        }

    public void setImage(String image) {
        this.image = image;
    }
}
