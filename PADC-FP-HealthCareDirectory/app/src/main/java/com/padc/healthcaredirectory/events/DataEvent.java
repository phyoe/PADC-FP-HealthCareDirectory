package com.padc.healthcaredirectory.events;

import android.util.Log;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class DataEvent {

    public static class HealthCareServiceDataLoadedEvent {

        private String extraMessage;
        private List<HealthCareServiceVO> mHealthCareServiceList;

        public HealthCareServiceDataLoadedEvent(String extraMessage, List<HealthCareServiceVO> healthCareServiceList) {
            this.extraMessage = extraMessage;
            this.mHealthCareServiceList = healthCareServiceList;
        }

        public String getExtraMessage() {
            return extraMessage;
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
    }

    public static class HealthCareInfoDataLoadedEvent {

        private String extraMessage;
        private List<HealthCareInfoVO> mHealthCareInfoList;

        public HealthCareInfoDataLoadedEvent(String extraMessage, List<HealthCareInfoVO> healthCareInfoList) {
            this.extraMessage = extraMessage;
            this.mHealthCareInfoList = healthCareInfoList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<HealthCareInfoVO> getHealthCareInfoList() {
            return mHealthCareInfoList;
        }
    }
}
