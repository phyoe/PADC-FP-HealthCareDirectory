package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.DoctorCategoryVO;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.utils.CommonInstances;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorCategoryModel {
    private static final String DUMMY_DOCTOR_CATEGORY_LIST = "doctorcategories.json";

    private static DoctorCategoryModel objInstance;

    private List<DoctorCategoryVO> mDoctorCategoryList;

    public DoctorCategoryModel() {
        super();
        mDoctorCategoryList = initializeDoctorCategoryList();;
    }

    public static DoctorCategoryModel getInstance(){
        if(objInstance == null){
            objInstance = new DoctorCategoryModel();
        }
        return objInstance;
    }

    private List<DoctorCategoryVO> initializeDoctorCategoryList() {
        List<DoctorCategoryVO> doctorCategoryList = new ArrayList<>();

        try {
            String dummyDoctorCategoryList = JsonUtils.getInstance().loadDummyData(DUMMY_DOCTOR_CATEGORY_LIST);
            Type listType = new TypeToken<List<DoctorCategoryVO>>() {
            }.getType();
            doctorCategoryList = CommonInstances.getGsonInstance().fromJson(dummyDoctorCategoryList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctorCategoryList;
    }

    public List<DoctorCategoryVO> getDoctorCategoryList() {
        return mDoctorCategoryList;
    }

    public DoctorCategoryVO getDoctorCategoryById(int id) {
        for (DoctorCategoryVO doctorCategory : mDoctorCategoryList) {
            if (doctorCategory.getId() == id){
                return doctorCategory;
            }
        }
        return null;
    }
}
