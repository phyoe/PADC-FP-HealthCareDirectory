package com.padc.healthcaredirectory.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;

import java.util.ArrayList;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareServiceListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("health-care-services")
    private ArrayList<HealthCareServiceVO> healthCareServiceList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HealthCareServiceVO> getHealthCareServiceList() {
        return healthCareServiceList;
    }
}
