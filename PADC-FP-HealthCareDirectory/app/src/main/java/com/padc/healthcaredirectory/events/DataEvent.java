package com.padc.healthcaredirectory.events;

import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;

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

        public List<HealthCareServiceVO> getHealthCareServiceList() {
            return mHealthCareServiceList;
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
