package com.padc.healthcaredirectory.data.agents;

import com.padc.healthcaredirectory.responses.HealthCareInfoListResponse;
import com.padc.healthcaredirectory.responses.HealthCareServiceListResponse;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public interface HealthCareApi {

    @FormUrlEncoded
    @POST(HealthCareDirectoryConstants.API_GET_HEALTHCARE_SERVICES_LIST)
    Call<HealthCareServiceListResponse> loadHealthCareServices(
            @Field(HealthCareDirectoryConstants.PARAM_ACCESS_TOKEN) String param);

    @FormUrlEncoded
    @POST(HealthCareDirectoryConstants.API_GET_HEALTHCARE_INFO_LIST)
    Call<HealthCareInfoListResponse> loadHealthCareInfos(
            @Field(HealthCareDirectoryConstants.PARAM_ACCESS_TOKEN) String param);
}
