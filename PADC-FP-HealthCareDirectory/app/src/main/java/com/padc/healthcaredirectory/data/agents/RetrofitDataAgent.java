package com.padc.healthcaredirectory.data.agents;

import android.util.Log;

import com.padc.healthcaredirectory.data.models.HealthCareInfoModel;
import com.padc.healthcaredirectory.data.models.HealthCareServiceModel;
import com.padc.healthcaredirectory.data.responses.HealthCareInfoListResponse;
import com.padc.healthcaredirectory.data.responses.HealthCareServiceListResponse;
import com.padc.healthcaredirectory.utils.CommonInstance;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements HealthCareDataAgent {

    private static RetrofitDataAgent objInstance;

    private final HealthCareApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HealthCareDirectoryConstants.HEALTHCARE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstance.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(HealthCareApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadHealthCareServices() {
        Call<HealthCareServiceListResponse> loadHealthCareServiceCall = theApi.loadHealthCareServices(HealthCareDirectoryConstants.ACCESS_TOKEN);
        loadHealthCareServiceCall.enqueue(new Callback<HealthCareServiceListResponse>() {
            @Override
            public void onResponse(Call<HealthCareServiceListResponse> call, Response<HealthCareServiceListResponse> response) {

                if (response.isSuccessful()) {
                    HealthCareServiceListResponse healthCareServiceListResponse = response.body();
                    if (healthCareServiceListResponse == null) {
                        HealthCareServiceModel.getInstance().notifyErrorInLoadingHealthCareService(response.message());
                    } else {
                        HealthCareServiceModel.getInstance().notifyHealthCareServiceLoaded(healthCareServiceListResponse.getHealthCareServiceList());
                    }
                } else {
                    Log.e("DataAgent", "Loading Health Care Service Not Success");
                }
            }

            @Override
            public void onFailure(Call<HealthCareServiceListResponse> call, Throwable throwable) {
                Log.e("DataAgent", "Loading Health Care Service Failure : " + throwable.getMessage());
                throwable.printStackTrace();
                HealthCareServiceModel.getInstance().notifyErrorInLoadingHealthCareService(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadHealthCareInfos() {
        Call<HealthCareInfoListResponse> loadHealthCareInfoCall = theApi.loadHealthCareInfos(HealthCareDirectoryConstants.ACCESS_TOKEN);
        loadHealthCareInfoCall.enqueue(new Callback<HealthCareInfoListResponse>() {
            @Override
            public void onResponse(Call<HealthCareInfoListResponse> call, Response<HealthCareInfoListResponse> response) {
                if (response.isSuccessful()) {
                    HealthCareInfoListResponse healthCareInfoListResponse = response.body();
                    if (healthCareInfoListResponse == null) {
                        HealthCareInfoModel.getInstance().notifyErrorInLoadingHealthCareInfo(response.message());
                    } else {
                        HealthCareInfoModel.getInstance().notifyHealthCareInfoLoaded(healthCareInfoListResponse.getHealthCareInfoList());
                    }
                } else {
                    Log.e("DataAgent", "Loading Health Care Info Not Success");
                }
            }

            @Override
            public void onFailure(Call<HealthCareInfoListResponse> call, Throwable throwable) {
                Log.e("DataAgent", "Loading Health Care Info Failure : " + throwable.getMessage());
                throwable.printStackTrace();
                HealthCareInfoModel.getInstance().notifyErrorInLoadingHealthCareInfo(throwable.getMessage());
            }
        });
    }
}
