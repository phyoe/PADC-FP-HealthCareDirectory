package com.padc.healthcaredirectory.utils;

/**
 * Created by Phyoe Khant on 9/9/2016.
 */
public class HealthCareDirectoryConstants {

    public static final String HEALTHCARE_BASE_URL = "http://www.aungpyaephyo.xyz/padc-fp/healthcare-directory/";
    public static final String API_GET_HEALTHCARE_SERVICES_LIST = "GetHealthcareServices.php";
    public static final String API_GET_HEALTHCARE_INFO_LIST = "GetHealthcareInfo.php";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    //Loader ID
    public static final int HEALTHCARE_SERVICE_LIST_LOADER = 1;
    public static final int HEALTHCARE_SERVICE_DETAIL_LOADER = 2;
    public static final int HEALTHCARE_INFO_LIST_LOADER = 3;
    public static final int HEALTHCARE_INFO_DETAIL_LOADER = 4;
    public static final int HEALTHCARE_INFO_DETAIL_WEBVIEW_LOADER =5;

    //Fragment
    public static final int FRAGMENT_HOSPITAL = 1;
    public static final int FRAGMENT_CLINIC = 2;
    public static final int FRAGMENT_PHARMACY = 3;

    public static final String STR_HOSPITAL = "hospital";
    public static final String STR_CLINIC = "clinic";
    public static final String STR_PHARMACY = "pharmacy";
    public static final String STR_ARTICLE = "article";
    public static final String STR_DISEASE = "disease";
    public static final String STR_VET = "vet";
    public static final String STR_NO_DATA = "( မရွိေသးပါ )";

    //Veterinary Activity
    public static final String STR_VET_CLINIC="VeterinaryClinic";
    public static final String STR_VET_EQUIPMENT="VeterinaryEquipment";
    public static final String STR_VET_MEDICINE="VeterinaryMedicine";
    public static final String STR_VET_SPA="VeterinarySpa";
}
