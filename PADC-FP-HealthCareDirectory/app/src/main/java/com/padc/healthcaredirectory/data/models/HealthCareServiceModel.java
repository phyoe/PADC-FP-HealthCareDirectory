package com.padc.healthcaredirectory.data.models;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.utils.CommonInstance;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Phyoe Khant on 9/21/2016.
 */
public class HealthCareServiceModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static final String DUMMY_HEALTHCARE_SERVICE_LIST = "health-care-services.json";

    private static HealthCareServiceModel objInstance;

    private List<HealthCareServiceVO> mHealthCareServiceList;

    public HealthCareServiceModel() {
        /**/
        //From Network Layer
        Log.d("Data", "data");
        mHealthCareServiceList = new ArrayList<>();
        dataAgent.loadHealthCareServices();
        /**/

        /**
         //From Json file
         super();
         mHealthCareServiceList = initializeHealthCareService();
         /**/
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
            healthCareServiceList = CommonInstance.getGsonInstance().fromJson(dummyHealthCareServiceList, listType);
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

    public List<HealthCareServiceVO> getHealthCareServiceList(String category) {

        //Filter here
        List<HealthCareServiceVO> filterList = new ArrayList<>();
        for(HealthCareServiceVO healthcareService: mHealthCareServiceList)
        {
            if(healthcareService.getCategory()==null)
            {
                healthcareService.setCategory(HealthCareDirectoryConstants.STR_HOSPITAL);
            }
            if(healthcareService.getCategory().equals(category) )
            {
                filterList.add(healthcareService);
                Log.d(HealthCareDirectoryApp.TAG,healthcareService.getCategory() );
            }
        }

        return filterList;
    }

    public List<HealthCareServiceVO> getHealthCareServiceListBySearchText(String searchText) {
        //Filter here
        List<HealthCareServiceVO> filterList = new ArrayList<>();
        for(HealthCareServiceVO healthcareService: mHealthCareServiceList)
        {
            if(healthcareService.getHealthCareName().contains(searchText) )
            {
                filterList.add(healthcareService);
                Log.d(HealthCareDirectoryApp.TAG,healthcareService.getHealthCareName() );
            }
        }

        return filterList;
    }

    /**
     * for Network Layer
     */
    public void notifyHealthCareServiceLoaded(List<HealthCareServiceVO> healthCareServiceList) {
        //Notify that the data is ready - using LocalBroadcast
        mHealthCareServiceList = healthCareServiceList;

        //keep the data in persistent layer.
        HealthCareServiceVO.saveHealthCareService(mHealthCareServiceList);

        broadcastHealthCareServiceLoadedWithEventBus();
    }

    public void notifyErrorInLoadingHealthCareService(String message) {

    }

    private void broadcastHealthCareServiceLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.HealthCareServiceDataLoadedEvent("extra-in-broadcast", mHealthCareServiceList));
    }

    public void loadHealthCareServices()
    {
        dataAgent.loadHealthCareServices();
    }

    /**
     * for Persistence Layer
     */
    public void setStoredData(List<HealthCareServiceVO> healthCareServiceList) {
        mHealthCareServiceList = healthCareServiceList;
    }
}
