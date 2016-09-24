package com.padc.healthcaredirectory.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;

import java.util.ArrayList;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareInfoListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("health-care-info")
    private ArrayList<HealthCareInfoVO> healthCareInfoList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HealthCareInfoVO> getHealthCareInfoList() {
        return healthCareInfoList;
    }
}
