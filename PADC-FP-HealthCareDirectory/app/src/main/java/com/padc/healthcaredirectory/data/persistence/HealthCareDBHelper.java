package com.padc.healthcaredirectory.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoAuthorEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceFaxEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServicePhoneEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceTagEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceOperationEntry;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "healthcares.db";

    private static final String SQL_CREATE_HEALTHCARE_SERVICES_TABLE = "CREATE TABLE " + HealthCareServiceEntry.TABLE_NAME + " (" +
            HealthCareServiceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_ID + " INTEGER NOT NULL, "+
            HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME + " TEXT NOT NULL, "+
            HealthCareServiceEntry.COLUMN_CATEGORY + " TEXT NOT NULL, "+
            HealthCareServiceEntry.COLUMN_CATEGORY_MM + " TEXT NOT NULL, "+
            HealthCareServiceEntry.COLUMN_IMAGE + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_ADDRESS + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_EMAIL + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_WEBSITE + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_MAPINFO + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_FACEBOOK + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_REMARK + " TEXT NULL, "+
            HealthCareServiceEntry.COLUMN_PRICE_CATEGORY + " INTEGER NULL, "+

            " UNIQUE (" + HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_PHONES_TABLE = "CREATE TABLE " + HealthCareServicePhoneEntry.TABLE_NAME + " (" +
            HealthCareServicePhoneEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServicePhoneEntry.COLUMN_PHONE_ID + " INTEGER NULL, "+
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServicePhoneEntry.COLUMN_PHONE_NAME + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServicePhoneEntry.COLUMN_PHONE_ID + ", "+
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";


    private static final String SQL_CREATE_HEALTHCARE_SERVICE_FAX_TABLE = "CREATE TABLE " + HealthCareServiceFaxEntry.TABLE_NAME + " (" +
            HealthCareServiceFaxEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceFaxEntry.COLUMN_FAX_ID + " INTEGER NULL, "+
            HealthCareServiceFaxEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceFaxEntry.COLUMN_FAX_NAME + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceFaxEntry.COLUMN_FAX_ID + ", "+
            HealthCareServiceFaxEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_TAGS_TABLE = "CREATE TABLE " + HealthCareServiceTagEntry.TABLE_NAME + " (" +
            HealthCareServiceTagEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceTagEntry.COLUMN_TAG_ID + " INTEGER NULL, "+
            HealthCareServiceTagEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceTagEntry.COLUMN_TAG_NAME + " TEXT NULL, "+
            HealthCareServiceTagEntry.COLUMN_TAG_NAME_MM + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceTagEntry.COLUMN_TAG_ID + ", "+
            HealthCareServiceTagEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_OPERATIONS_TABLE = "CREATE TABLE " + HealthCareServiceOperationEntry.TABLE_NAME + " (" +
            HealthCareServiceOperationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceOperationEntry.COLUMN_OPERATION_ID + " INTEGER NULL, "+
            HealthCareServiceOperationEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceOperationEntry.COLUMN_OPERATION_NAME + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceOperationEntry.COLUMN_OPERATION_ID + ", "+
            HealthCareServiceOperationEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    /**
    private static final String SQL_CREATE_HEALTHCARE_SERVICE_DOCTORS_TABLE = "CREATE TABLE " + HealthCareServiceDoctorEntry.TABLE_NAME + " (" +
            HealthCareServiceDoctorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceDoctorEntry.COLUMN_DOCTOR_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorEntry.COLUMN_DOCTOR_NAME + " TEXT NULL, "+
            HealthCareServiceDoctorEntry.COLUMN_DOCTOR_TITLE + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceDoctorEntry.COLUMN_DOCTOR_ID + ", "+
            HealthCareServiceDoctorEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_DOCTOR_SPECIALITIES_TABLE = "CREATE TABLE " + HealthCareServiceDoctorSpecialityEntry.TABLE_NAME + " (" +
            HealthCareServiceDoctorSpecialityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_DOCTOR_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_TITLE + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_ID + ", "+
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_DOCTOR_TIMESLOTS_TABLE = "CREATE TABLE " + HealthCareServiceDoctorTimeslotEntry.TABLE_NAME + " (" +
            HealthCareServiceDoctorTimeslotEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_DOCTOR_ID + " INTEGER NULL, "+
            HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_TIME + " TEXT NULL, "+
            HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_DATE + " TEXT NULL, "+

            " UNIQUE (" + HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_ID + ", "+
            HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";
    /**/
    private static final String SQL_CREATE_HEALTHCARE_INFOS_TABLE = "CREATE TABLE " + HealthCareInfoEntry.TABLE_NAME + " (" +
            HealthCareInfoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareInfoEntry.COLUMN_HEALTHCARE_INFO_ID + " INTEGER NOT NULL, "+
            HealthCareInfoEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            HealthCareInfoEntry.COLUMN_IMAGE + " TEXT NULL, "+
            HealthCareInfoEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL, "+
            HealthCareInfoEntry.COLUMN_PUBLISHED_DATE + " TEXT NULL, "+
            HealthCareInfoEntry.COLUMN_COMPLETE_URL + " TEXT NULL, "+
            HealthCareInfoEntry.COLUMN_INFO_TYPE + " TEXT NULL, "+

            " UNIQUE (" + HealthCareInfoEntry.COLUMN_HEALTHCARE_INFO_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_INFO_AUTHORS_TABLE = "CREATE TABLE " + HealthCareInfoAuthorEntry.TABLE_NAME + " (" +
            HealthCareInfoAuthorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareInfoAuthorEntry.COLUMN_AUTHOR_ID + " INTEGER NOT NULL, "+
            HealthCareInfoAuthorEntry.COLUMN_INFO_ID + " INTEGER NOT NULL, "+
            HealthCareInfoAuthorEntry.COLUMN_AUTHOR_NAME + " TEXT NOT NULL, "+
            HealthCareInfoAuthorEntry.COLUMN_AUTHOR_PICTURE + " TEXT NULL, "+

            " UNIQUE (" + HealthCareInfoAuthorEntry.COLUMN_AUTHOR_ID + ", "+
            HealthCareInfoAuthorEntry.COLUMN_INFO_ID + ") ON CONFLICT IGNORE" +
            " );";

    public HealthCareDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_PHONES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_FAX_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_TAGS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_OPERATIONS_TABLE);
        //sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_DOCTORS_TABLE);
        //sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_DOCTOR_SPECIALITIES_TABLE);
        //sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_DOCTOR_TIMESLOTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_INFOS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_INFO_AUTHORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServicePhoneEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceFaxEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceTagEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceOperationEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceDoctorEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceDoctorSpecialityEntry.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceDoctorTimeslotEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareInfoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareInfoAuthorEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
