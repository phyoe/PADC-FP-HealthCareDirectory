package com.padc.healthcaredirectory.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServicePhoneEntry;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "healthcares.db";

    private static final String SQL_CREATE_HEALTHCARE_SERVICES_TABLE = "CREATE TABLE " + HealthCareServiceEntry.TABLE_NAME + " (" +
            HealthCareServiceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServiceEntry.COLUMN_NAME + " TEXT NOT NULL, "+
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

            " UNIQUE (" + HealthCareServiceEntry.COLUMN_NAME + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_HEALTHCARE_SERVICE_PHONES_TABLE = "CREATE TABLE " + HealthCareServicePhoneEntry.TABLE_NAME + " (" +
            HealthCareServicePhoneEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + " TEXT NOT NULL, "+
            HealthCareServicePhoneEntry.COLUMN_NAME + " TEXT NOT NULL, "+

            " UNIQUE (" + HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + ", "+
            HealthCareServicePhoneEntry.COLUMN_NAME + ") ON CONFLICT IGNORE" +
            " );";

    public HealthCareDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTHCARE_SERVICE_PHONES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServiceEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthCareServicePhoneEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
