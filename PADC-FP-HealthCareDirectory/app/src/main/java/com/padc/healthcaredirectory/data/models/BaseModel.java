package com.padc.healthcaredirectory.data.models;

import com.padc.healthcaredirectory.data.agents.HealthCareDataAgent;
import com.padc.healthcaredirectory.data.agents.RetrofitDataAgent;

import de.greenrobot.event.EventBus;

/**
 * Created by aung on 7/15/16.
 */
public abstract class BaseModel {

    private static final int INIT_DATA_AGENT_RETROFIT = 4;

    protected HealthCareDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {
            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    public void onEventMainThread(Object obj) {

    }

}