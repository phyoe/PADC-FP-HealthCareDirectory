package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.utils.CommonInstances;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareModel {

    private static final String DUMMY_HOSPITAL_LIST = "hospitals.json";
    private static final String DUMMY_CLINIC_LIST = "clinics.json";
    private static final String DUMMY_PHARMACY_LIST = "pharmacies.json";

    private static HealthCareModel objInstance;

    private List<HealthCareVO> mHealthCareList;

    public HealthCareModel() {
        super();
        mHealthCareList = initializeHealthCareList();
    }

    public static HealthCareModel getInstance(){
        if(objInstance == null){
            objInstance = new HealthCareModel();
        }
        return objInstance;
    }
    private List<HealthCareVO> initializeHealthCareList() {
        List<HealthCareVO> healthCareList = new ArrayList<>();

        try {
            String dummyHealthCareList = JsonUtils.getInstance().loadDummyData(DUMMY_HOSPITAL_LIST);
            Type listType = new TypeToken<List<HealthCareVO>>() {
            }.getType();
            healthCareList = CommonInstances.getGsonInstance().fromJson(dummyHealthCareList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return healthCareList;

        /**
        List<Restarutant> list = new ArrayList<Restarutant>();
        try {
            JSONObject json = new JSONObject(thepreviousjson);
            JSONArray jArray = json.getJSONArray("restaurant");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Restarutant data = new Restarutant();// Create Object Here
                data.setName(json_data.getString("name"));
                data.setPhotoUrl(json_data.getString("photo"));
                data.setAddress(json_data.getString("address"));
                data.setArea(json_data.getString("area"));
                data.setCity(json_data.getString("city"));
                JSONObject cuisines = json_data.getJSONObject("cuisines");
                Cuisines cuisine = new Cuisines();// Create Object here
                cuisine.setFirst(cuisines.getString("first"));
                cuisine.setSecond(cuisines.getString("second"));
                data.setCuisines(cuisine);// setting the cuisine
                list.add(data);// Finally adding the model to List

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**/

    }


    public List<HealthCareVO> getHealthCareList() {
        return mHealthCareList;
    }

    public HealthCareVO getHealthCareById(int id) {
        for (HealthCareVO healthcare : mHealthCareList) {
            if (healthcare.getId() == id) {
                return healthcare;
            }
        }
        return null;
    }

}
