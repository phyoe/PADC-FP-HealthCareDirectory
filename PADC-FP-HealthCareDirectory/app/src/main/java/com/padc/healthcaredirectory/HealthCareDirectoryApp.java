package com.padc.healthcaredirectory;

import android.app.Application;
import android.content.Context;

/**
 * Created by Phyoe Khant on 9/4/2016.
 */
public class HealthCareDirectoryApp extends Application {

    public static String TAG = "HealthCareDirectoryApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //From Network Layer
        //RetrofitDataAgent.getInstance().loadHealthCareServices();
        //RetrofitDataAgent.getInstance().loadHealthCareInfos();
    }

    public static Context getContext() {
        return context;
    }
}
