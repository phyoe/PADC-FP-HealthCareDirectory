package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class SpecialityVO {

    @SerializedName("speciality-id")
    private int specialityId;

    @SerializedName("speciality-title")
    private String specialityTitle;

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getSpecialityTitle() {
        return specialityTitle;
    }

    public void setSpecialityTitle(String specialityTitle) {
        this.specialityTitle = specialityTitle;
    }
}
