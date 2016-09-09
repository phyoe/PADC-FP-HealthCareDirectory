package com.padc.healthcaredirectory.data.models;

import com.padc.healthcaredirectory.data.vos.HealthCareVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareModel {

    private static HealthCareModel objInstance;

    private List<HealthCareVO> mHealthCareList;

    public HealthCareModel() {
        super();
        mHealthCareList = new ArrayList<>();
    }

    public static HealthCareModel getInstance(){
        if(objInstance == null){
            objInstance = new HealthCareModel();
        }
        return objInstance;
    }

    public List<HealthCareVO> getHealthCareList() {
        return mHealthCareList;
    }

}
