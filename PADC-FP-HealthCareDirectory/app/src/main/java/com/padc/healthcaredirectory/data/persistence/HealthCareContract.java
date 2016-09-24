package com.padc.healthcaredirectory.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareContract {
    public static final String CONTENT_AUTHORITY = HealthCareDirectoryApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HEALTHCARE_SERVICES = "healthcare_services";
    public static final String PATH_HEALTHCARE_SERVICE_PHONES = "healthcare_service_phones";
    public static final String PATH_HEALTHCARE_SERVICE_FAX = "healthcare_service_fax";
    public static final String PATH_HEALTHCARE_SERVICE_TAGS = "healthcare_service_tags";
    public static final String PATH_HEALTHCARE_SERVICE_OPERATIONS = "healthcare_service_operations";

    public static final String PATH_HEALTHCARE_SERVICE_DOCTORS = "healthcare_service_doctors";
    public static final String PATH_HEALTHCARE_SERVICE_DOCTOR_SPECIALITIES = "healthcare_service_doctor_specialities";
    public static final String PATH_HEALTHCARE_SERVICE_DOCTOR_TIMESLOTS = "healthcare_service_doctor_timeslots";

    public static final String PATH_HEALTHCARE_INFOS = "healthcare_infos";
    public static final String PATH_HEALTHCARE_INFO_AUTHORS = "healthcare_info_authors";

    public static final class HealthCareServiceEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HEALTHCARE_SERVICES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_SERVICES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_SERVICES;

        public static final String TABLE_NAME = "healthcare_services";

        public static final String COLUMN_ID                = "healthcare_service_id";
        public static final String COLUMN_NAME              = "healthcare_service_name";
        public static final String COLUMN_CATEGORY          = "category";
        public static final String COLUMN_CATEGORY_MM       = "category_mm";
        public static final String COLUMN_IMAGE             = "image";
        public static final String COLUMN_ADDRESS           = "address";
        public static final String COLUMN_EMAIL             = "email";
        public static final String COLUMN_WEBSITE           = "website";
        public static final String COLUMN_MAPINFO           = "mapinfo";
        public static final String COLUMN_FACEBOOK          = "facebook";
        public static final String COLUMN_REMARK            = "remark";
        public static final String COLUMN_PRICE_CATEGORY    = "price_category";

        public static Uri buildHealthCareServiceUri(long id) {
            //content://com.padc.healthcaredirectory/healthcare_services/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHealthCareServiceUriWithName(String healthCareServiceName) {
            //content://com.padc.healthcaredirectory/healthcare_services?healthcare_service_name=Asiaroyal
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, healthCareServiceName)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    public static final class HealthCareServicePhoneEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HEALTHCARE_SERVICE_PHONES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_SERVICE_PHONES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_SERVICE_PHONES;

        public static final String TABLE_NAME = "healthcare_service_phones";

        public static final String COLUMN_ID             = "phone_id";
        public static final String COLUMN_SERVICE_ID     = "healthcare_service_id";
        public static final String COLUMN_NAME           = "phone_name";

        public static Uri buildServicePhoneUri(long id) {
            //content://com.padc.healthcaredirectory/healthcare_service_phones/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHealthCareServicePhoneUriWithParentId(String healthCareServiceId) {
            //content://com.padc.healthcaredirectory/healthcare_service_phones?healthcare_service_id=xxxx
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_SERVICE_ID, healthCareServiceId)
                    .build();
        }
        public static String getHealthCareServiceIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_SERVICE_ID);
        }
    }

    public static final class HealthCareServiceFaxEntry implements BaseColumns{

    }

    public static final class HealthCareServiceTagEntry implements BaseColumns{

    }

    public static final class HealthCareServiceOperationEntry implements BaseColumns{

    }

    public static final class HealthCareServiceDoctorEntry implements BaseColumns{

    }

    public static final class HealthCareServiceDoctorSpecialityEntry implements BaseColumns{

    }

    public static final class HealthCareServiceDoctorTimeslotEntry implements BaseColumns{

    }

    public static final class HealthCareInfoEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HEALTHCARE_INFOS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_INFOS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHCARE_INFOS;

        public static final String TABLE_NAME = "healthcare_infos";

        public static final String COLUMN_ID                = "healthcare_info_id";
        public static final String COLUMN_TITLE             = "title";
        public static final String COLUMN_SHORT_DESC        = "short_description";
        public static final String COLUMN_PUBLISHED_DATE    = "published_date";
        public static final String COLUMN_COMPLETE_URL      = "complete_url";
        public static final String COLUMN_INFO_TYPE         = "info_type";

        public static Uri buildHealthCareInfoUri(long id) {
            //content://com.padc.healthcaredirectory/healthcare_infos/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHealthCareInfoUriWithTitle(String title) {
            //content://com.padc.healthcaredirectory/healthcare_services?title=Asiaroyal
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, title)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class HealthCareInfoAuthorEntry implements BaseColumns{

    }
}
