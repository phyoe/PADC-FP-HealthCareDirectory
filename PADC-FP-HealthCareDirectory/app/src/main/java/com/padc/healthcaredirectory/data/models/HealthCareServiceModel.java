package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.PhoneVO;
import com.padc.healthcaredirectory.utils.CommonInstances;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/21/2016.
 */
public class HealthCareServiceModel {

    private static final String DUMMY_HEALTHCARE_SERVICE_LIST = "health-care-services.json";

    private static HealthCareServiceModel objInstance;

    private List<HealthCareServiceVO> mHealthCareServiceList;
    private List<PhoneVO> mPhoneList;
    
    public HealthCareServiceModel() {
        super();
        mHealthCareServiceList = initializeHealthCareService();
    }

    public static HealthCareServiceModel getInstance(){
        if(objInstance == null){
            objInstance = new HealthCareServiceModel();
        }
        return objInstance;
    }

    private List<HealthCareServiceVO> initializeHealthCareService() {

        List<HealthCareServiceVO> healthCareServiceList = new ArrayList<>();
        //ArrayList<HealthCareServiceVO> list = new ArrayList<HealthCareServiceVO>();

        try {

            String dummyHealthCareServiceList = JsonUtils.getInstance().loadDummyData(DUMMY_HEALTHCARE_SERVICE_LIST);
            Type listType = new TypeToken<List<HealthCareServiceVO>>() {
            }.getType();
            healthCareServiceList = CommonInstances.getGsonInstance().fromJson(dummyHealthCareServiceList, listType);
            /**/

            /**
            try {
                JSONObject json = new JSONObject(dummyHealthCareServiceList);
                JSONArray jArray = json.getJSONArray("health-care-info");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    HealthCareServiceVO data = new HealthCareServiceVO();// Create Object Here
                    data.setHealthCareId(json_data.getInt("health-care-id"));
                    data.setHealthCareName(json_data.getString("health-care-name"));
                    data.setCategory(json_data.getString("category"));
                    data.setCategoryMM(json_data.getString("category-mm"));
                    data.setImage(json_data.getString("image"));
                    data.setAddress(json_data.getString("address"));
                    data.setEmail(json_data.getString("email"));
                    data.setWebsite(json_data.getString("website"));
                    data.setMapinfo(json_data.getString("mapinfo"));
                    data.setFacebook(json_data.getString("facebook"));
                    data.setRemark(json_data.getString("remark"));

                    JSONObject phones = json_data.getJSONObject("phones");
                    ArrayList<PhoneVO> phoneList = new ArrayList<PhoneVO>();// Create Object here
                    for (int j = 0; j < phones.length(); j++) {
                        PhoneVO phone = new PhoneVO();// Create Object here

                        phone.setPhoneId(phones.getInt("phone-id"));
                        phone.setPhoneName(phones.getString("phone-name"));
                        phoneList.add(phone);// setting the phone
                    }
                    data.setPhones(phoneList);
                    list.add(data);// Finally adding the model to List
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            /**/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return healthCareServiceList;
        //return list;
    }

    public List<HealthCareServiceVO> getHealthCareServiceList() {
        return mHealthCareServiceList;
    }

    public HealthCareServiceVO getHealthCareServiceById(int id) {
        for (HealthCareServiceVO healthcare : mHealthCareServiceList) {
            if (healthcare.getHealthCareId() == id) {
                return healthcare;
            }
        }
        return null;
    }

    public PhoneVO getPhonesByHealthCareServiceId(int id) {
        for (PhoneVO phone : mPhoneList) {
           // if (phone.getId() == id) {
             //   return healthcare;
           // }
        }
        return null;
    }

}
