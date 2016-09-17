package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorCategoryVO {

    @SerializedName("id")
    int id;

    @SerializedName("category")
    String category;

    @SerializedName("category_mm")
    String categoryMM;

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public String getCategoryMM() {
        return categoryMM;
    }
}
