package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/13/2016.
 */
public class CategoryVO {

    @SerializedName("title")
    private String title;

    @SerializedName("mm_title")
    private String mmTitle;

    @SerializedName("categories")
    private SubCategoryVO categories;

    public String getTitle() {
        return title;
    }

    public String getMmTitle() {
        return mmTitle;
    }

    public SubCategoryVO getCategories() {
        return categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMmTitle(String mmTitle) {
        this.mmTitle = mmTitle;
    }

    public void setCategories(SubCategoryVO categories) {
        this.categories = categories;
    }
}
