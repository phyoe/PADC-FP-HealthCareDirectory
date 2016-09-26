package com.padc.healthcaredirectory.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServicePhoneEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoAuthorEntry;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareProvider extends ContentProvider {

    public static final int HEALTHCARE_SERVICES = 100;
    public static final int HEALTHCARE_SERVICE_PHONES = 200;
    public static final int HEALTHCARE_INFOS = 300;
    public static final int HEALTHCARE_INFO_AUTHORS = 400;

    private static final String sHealthcareServiceNameSelection = HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME + " = ?";
    private static final String sHealthcareServicePhoneSelectionWithServiceId = HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + " = ?";
    private static final String sHealthcareInfoTitleSelection = HealthCareInfoEntry.COLUMN_TITLE + " = ?";
    private static final String sHealthcareInfoAuthorSelectionWithInfoId = HealthCareInfoAuthorEntry.COLUMN_INFO_ID + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private HealthCareDBHelper mHealthCareDBHelper;

    @Override
    public boolean onCreate() {
        mHealthCareDBHelper = new HealthCareDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case HEALTHCARE_SERVICES:
                String healthCareServiceName = HealthCareServiceEntry.getNameFromParam(uri);
                if (!TextUtils.isEmpty(healthCareServiceName)) {
                    selection = sHealthcareServiceNameSelection;
                    selectionArgs = new String[]{healthCareServiceName};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_PHONES:
                String service_id = HealthCareServicePhoneEntry.getHealthCareServiceIdFromParam(uri);
                if (service_id != null) {
                    selection = sHealthcareServicePhoneSelectionWithServiceId;
                    selectionArgs = new String[]{service_id};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServicePhoneEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_INFOS:
                String healthCareInfoName = HealthCareInfoEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(healthCareInfoName)) {
                    selection = sHealthcareInfoTitleSelection;
                    selectionArgs = new String[]{healthCareInfoName};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareInfoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case HEALTHCARE_INFO_AUTHORS:
                String info_id = HealthCareInfoAuthorEntry.getHealthCareInfoIdFromParam(uri);
                if (info_id != null) {
                    selection = sHealthcareInfoAuthorSelectionWithInfoId;
                    selectionArgs = new String[]{info_id};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareInfoAuthorEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case HEALTHCARE_SERVICES:
                return HealthCareContract.HealthCareServiceEntry.DIR_TYPE;
            case HEALTHCARE_SERVICE_PHONES:
                return HealthCareContract.HealthCareServicePhoneEntry.DIR_TYPE;
            case HEALTHCARE_INFOS:
                return HealthCareContract.HealthCareInfoEntry.DIR_TYPE;
            case HEALTHCARE_INFO_AUTHORS:
                return HealthCareContract.HealthCareInfoAuthorEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mHealthCareDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;
        switch (matchUri) {
            case HEALTHCARE_SERVICES: {
                long _id = db.insert(HealthCareContract.HealthCareServiceEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceEntry.buildHealthCareServiceUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_SERVICE_PHONES: {
                long _id = db.insert(HealthCareContract.HealthCareServicePhoneEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServicePhoneEntry.buildHealthCareServicePhoneUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_INFOS: {
                long _id = db.insert(HealthCareContract.HealthCareInfoEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareInfoEntry.buildHealthCareInfoUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_INFO_AUTHORS: {
                long _id = db.insert(HealthCareContract.HealthCareInfoAuthorEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareInfoAuthorEntry.buildHealthCareInfoAuthorUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mHealthCareDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mHealthCareDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mHealthCareDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICES, HEALTHCARE_SERVICES);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_PHONES, HEALTHCARE_SERVICE_PHONES);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_INFOS, HEALTHCARE_INFOS);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_INFO_AUTHORS, HEALTHCARE_INFO_AUTHORS);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case HEALTHCARE_SERVICES:
                return HealthCareContract.HealthCareServiceEntry.TABLE_NAME;
            case HEALTHCARE_SERVICE_PHONES:
                return HealthCareContract.HealthCareServicePhoneEntry.TABLE_NAME;
            case HEALTHCARE_INFOS:
                return HealthCareContract.HealthCareInfoEntry.TABLE_NAME;
            case HEALTHCARE_INFO_AUTHORS:
                return HealthCareContract.HealthCareInfoAuthorEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
