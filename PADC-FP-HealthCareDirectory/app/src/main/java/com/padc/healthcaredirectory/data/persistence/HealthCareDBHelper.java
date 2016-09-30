package com.padc.healthcaredirectory.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServicePhoneEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoAuthorEntry;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
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
            HealthCareServicePhoneEntry.COLUMN_PHONE_ID + " INTEGER NOT NULL, "+
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + " INTEGER NOT NULL, "+
            HealthCareServicePhoneEntry.COLUMN_PHONE_NAME + " TEXT NOT NULL, "+

            " UNIQUE (" + HealthCareServicePhoneEntry.COLUMN_PHONE_ID + ", "+
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";


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
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_INFOS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_INFO_AUTHORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServicePhoneEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareInfoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareInfoAuthorEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
