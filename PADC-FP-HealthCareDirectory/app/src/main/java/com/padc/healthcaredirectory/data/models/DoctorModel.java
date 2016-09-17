package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
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
public class DoctorModel {
    private static final String DUMMY_DOCTOR_LIST = "doctors.json";

    private static DoctorModel objInstance;

    private List<DoctorVO> mDoctorList;

    public DoctorModel() {
        super();
        mDoctorList = initializeDoctorList();;
    }

    public static DoctorModel getInstance(){
        if(objInstance == null){
            objInstance = new DoctorModel();
        }
        return objInstance;
    }

    private List<DoctorVO> initializeDoctorList() {
        List<DoctorVO> doctorList = new ArrayList<>();

        try {
            String dummyDoctorList = JsonUtils.getInstance().loadDummyData(DUMMY_DOCTOR_LIST);
            Type listType = new TypeToken<List<DoctorVO>>() {
            }.getType();
            doctorList = CommonInstances.getGsonInstance().fromJson(dummyDoctorList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    public List<DoctorVO> getDoctorList() {
        return mDoctorList;
    }

    public DoctorVO getDoctorById(int id) {
        for (DoctorVO doctor : mDoctorList) {
            if (doctor.getId() == id){
                return doctor;
            }
        }
        return null;
    }

    public DoctorVO getDoctorByCategoryName(String categoryName) {
        for (DoctorVO doctor : mDoctorList) {
            if (doctor.getCategory().equals(categoryName)){
                return doctor;
            }
        }
        return null;
    }
}
