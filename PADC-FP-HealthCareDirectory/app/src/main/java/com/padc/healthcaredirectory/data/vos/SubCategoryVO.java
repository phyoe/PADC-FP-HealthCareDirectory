package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/13/2016.
 */
public class SubCategoryVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("mm_name")
    private String mmName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMmName() {
        return mmName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMmName(String mmName) {
        this.mmName = mmName;
    }
}
