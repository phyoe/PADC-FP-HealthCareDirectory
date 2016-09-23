package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.AuthorVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.utils.CommonInstances;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareInfoModel {

    private static final String DUMMY_HEALTHCARE_INFO_LIST = "health-care-info.json";

    private static HealthCareInfoModel objInstance;

    private List<HealthCareInfoVO> mHealthCareInfoList;
    private List<AuthorVO> mAuthorList;

    public HealthCareInfoModel() {
        super();
        mHealthCareInfoList = initializeHealthCareInfo();
    }

    public static HealthCareInfoModel getInstance(){
        if(objInstance == null){
            objInstance = new HealthCareInfoModel();
        }
        return objInstance;
    }

    private List<HealthCareInfoVO> initializeHealthCareInfo() {
        List<HealthCareInfoVO> healthCareInfoList = new ArrayList<>();

        try {
            String dummyHealthCareInfoList = JsonUtils.getInstance().loadDummyData(DUMMY_HEALTHCARE_INFO_LIST);
            Type listType = new TypeToken<List<HealthCareInfoVO>>() {
            }.getType();
            healthCareInfoList = CommonInstances.getGsonInstance().fromJson(dummyHealthCareInfoList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return healthCareInfoList;
    }

    public List<HealthCareInfoVO> getHealthCareInfoList() {
        return mHealthCareInfoList;
    }

    public HealthCareInfoVO getHealthCareInfoById(int id) {
        for (HealthCareInfoVO healthcare : mHealthCareInfoList) {
            if (healthcare.getId() == id) {
                return healthcare;
            }
        }
        return null;
    }
}
