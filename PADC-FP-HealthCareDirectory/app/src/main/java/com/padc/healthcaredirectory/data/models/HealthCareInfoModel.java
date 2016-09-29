package com.padc.healthcaredirectory.data.models;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.AuthorVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.utils.CommonInstance;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareInfoModel extends BaseModel {
    //From Network Layer
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    //From Json file
    private static final String DUMMY_HEALTHCARE_INFO_LIST = "health-care-info.json";

    private static HealthCareInfoModel objInstance;

    private List<HealthCareInfoVO> mHealthCareInfoList;
    private List<AuthorVO> mAuthorList;

    public HealthCareInfoModel() {
        /**/
        //From Network Layer
        Log.d("Data", "data");
        mHealthCareInfoList = new ArrayList<>();
        dataAgent.loadHealthCareInfos();
        /**/

        /**
        //From Json file
        super();
        mHealthCareInfoList = initializeHealthCareInfo();
        /**/
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
            healthCareInfoList = CommonInstance.getGsonInstance().fromJson(dummyHealthCareInfoList, listType);

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

    public HealthCareInfoVO getHealthCareInfoById(long id) {
        for (HealthCareInfoVO healthcareInfo : mHealthCareInfoList) {
            if (healthcareInfo.getId() == id) {
                return healthcareInfo;
            }
        }
        return null;
    }

    /**
     * for Network Layer
     */
    public void notifyHealthCareInfoLoaded(List<HealthCareInfoVO> healthCareInfoList) {
        //Notify that the data is ready - using LocalBroadcast
        mHealthCareInfoList = healthCareInfoList;

        //keep the data in persistent layer.
        HealthCareInfoVO.saveHealthCareInfo(mHealthCareInfoList);

        broadcastHealthCareInfoLoadedWithEventBus();
    }

    public void notifyErrorInLoadingHealthCareInfo(String message) {

    }

    private void broadcastHealthCareInfoLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.HealthCareInfoDataLoadedEvent("extra-in-broadcast", mHealthCareInfoList));
    }

    public void loadHealthCareInfos()
    {
        dataAgent.loadHealthCareInfos();
    }

    /**
     * for Persistence Layer
     */
    public void setStoredData(List<HealthCareInfoVO> healthCareInfoList) {
        mHealthCareInfoList = healthCareInfoList;
    }
}
