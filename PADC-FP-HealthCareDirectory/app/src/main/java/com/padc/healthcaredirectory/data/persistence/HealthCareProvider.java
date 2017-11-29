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

import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoAuthorEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareInfoEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceOperationEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceFaxEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServicePhoneEntry;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract.HealthCareServiceTagEntry;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareProvider extends ContentProvider {

    public static final int HEALTHCARE_SERVICES = 100;
    public static final int HEALTHCARE_SERVICE_PHONES = 200;
    public static final int HEALTHCARE_SERVICE_FAX = 300;
    public static final int HEALTHCARE_SERVICE_TAGS = 400;
    public static final int HEALTHCARE_SERVICE_OPERATIONS = 500;
    public static final int HEALTHCARE_SERVICE_DOCTORS = 600;
    public static final int HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES = 700;
    public static final int HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS = 800;

    public static final int HEALTHCARE_INFOS = 900;
    public static final int HEALTHCARE_INFO_AUTHORS = 910;

    private static final String sHealthcareServiceNameSelection =
            HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME + " = ?";
    private static final String sHealthcareServicePhoneSelectionWithServiceId =
            HealthCareServicePhoneEntry.COLUMN_SERVICE_ID + " = ?";
    private static final String sHealthcareServiceFaxSelectionWithServiceId =
            HealthCareServiceFaxEntry.COLUMN_SERVICE_ID + " = ?";
    private static final String sHealthcareServiceTagSelectionWithServiceId =
            HealthCareServiceTagEntry.COLUMN_SERVICE_ID + " = ?";
    private static final String sHealthcareServiceOperationSelectionWithServiceId =
            HealthCareServiceOperationEntry.COLUMN_SERVICE_ID + " = ?";
    /**
    private static final String sHealthcareServiceDoctorSelectionWithServiceId =
            HealthCareServiceDoctorEntry.COLUMN_SERVICE_ID + " = ?";
    private static final String sHealthcareServiceDoctorSpecialitySelectionWithServiceId =
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_ID + " = ? AND " +
            HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_DOCTOR_ID + " = ?";
    private static final String sHealthcareServiceDoctorTimeslotSelectionWithServiceId =
            HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_ID + " = ? AND " +
            HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_DOCTOR_ID + " = ?";
    /**/

    private static final String sHealthcareInfoTitleSelection =
            HealthCareInfoEntry.COLUMN_TITLE + " = ?";
    private static final String sHealthcareInfoAuthorSelectionWithInfoId =
            HealthCareInfoAuthorEntry.COLUMN_INFO_ID + " = ?";

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
                String serviceIdPhone = HealthCareServicePhoneEntry.getHealthCareServiceIdFromParam(uri);
                if (serviceIdPhone != null) {
                    selection = sHealthcareServicePhoneSelectionWithServiceId;
                    selectionArgs = new String[]{serviceIdPhone};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServicePhoneEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_FAX:
                String serviceIdFax = HealthCareServiceFaxEntry.getHealthCareServiceIdFromParam(uri);
                if (serviceIdFax != null) {
                    selection = sHealthcareServiceFaxSelectionWithServiceId;
                    selectionArgs = new String[]{serviceIdFax};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceFaxEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_TAGS:
                String serviceIdTag = HealthCareServiceTagEntry.getHealthCareServiceIdFromParam(uri);
                if (serviceIdTag != null) {
                    selection = sHealthcareServiceTagSelectionWithServiceId;
                    selectionArgs = new String[]{serviceIdTag};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceTagEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_OPERATIONS:
                String serviceIdOperation = HealthCareServiceOperationEntry.getHealthCareServiceIdFromParam(uri);
                if (serviceIdOperation != null) {
                    selection = sHealthcareServiceOperationSelectionWithServiceId;
                    selectionArgs = new String[]{serviceIdOperation};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceOperationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            /**
            case HEALTHCARE_SERVICE_DOCTORS:
                String serviceIdDoctor = HealthCareServiceDoctorEntry.getHealthCareServiceIdFromParam(uri);
                if (serviceIdDoctor != null) {
                    selection = sHealthcareServiceDoctorSelectionWithServiceId;
                    selectionArgs = new String[]{serviceIdDoctor};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceDoctorEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES:
                String serviceIdDoctorSpe = HealthCareServiceDoctorSpecialityEntry.getHealthCareServiceIdFromParam(uri);
                //String doctorIdDoctorSpe = HealthCareServiceDoctorSpecialityEntry.getHealthCareServiceDoctorIdFromParam(uri);
                if (serviceIdDoctorSpe != null) {
                    selection = sHealthcareServiceDoctorSpecialitySelectionWithServiceId;
                    //selectionArgs = new String[]{serviceIdDoctorSpe, doctorIdDoctorSpe};
                    selectionArgs = new String[]{serviceIdDoctorSpe};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS:
                String serviceIdDoctorTimeSlot = HealthCareServiceDoctorTimeslotEntry.getHealthCareServiceIdFromParam(uri);
                //String doctorIdDoctorTimeSlot = HealthCareServiceDoctorTimeslotEntry.getHealthCareServiceDoctorIdFromParam(uri);
                if (serviceIdDoctorTimeSlot != null) {
                    selection = sHealthcareServiceDoctorTimeslotSelectionWithServiceId;
                    //selectionArgs = new String[]{serviceIdDoctorTimeSlot, doctorIdDoctorTimeSlot};
                    selectionArgs = new String[]{serviceIdDoctorTimeSlot};
                }
                queryCursor = mHealthCareDBHelper.getReadableDatabase().query(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            /**/
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
            case HEALTHCARE_SERVICE_FAX:
                return HealthCareContract.HealthCareServiceFaxEntry.DIR_TYPE;
            case HEALTHCARE_SERVICE_TAGS:
                return HealthCareContract.HealthCareServiceTagEntry.DIR_TYPE;
            case HEALTHCARE_SERVICE_OPERATIONS:
                return HealthCareContract.HealthCareServiceOperationEntry.DIR_TYPE;
            /**
            case HEALTHCARE_SERVICE_DOCTORS:
                return HealthCareContract.HealthCareServiceDoctorEntry.DIR_TYPE;
            case HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES:
                return HealthCareContract.HealthCareServiceDoctorSpecialityEntry.DIR_TYPE;
            case HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS:
                return HealthCareContract.HealthCareServiceDoctorTimeslotEntry.DIR_TYPE;
            /**/
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
            case HEALTHCARE_SERVICE_FAX: {
                long _id = db.insert(HealthCareContract.HealthCareServiceFaxEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceFaxEntry.buildHealthCareServiceFaxUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_SERVICE_TAGS: {
                long _id = db.insert(HealthCareContract.HealthCareServiceTagEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceTagEntry.buildHealthCareServiceTagUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_SERVICE_OPERATIONS: {
                long _id = db.insert(HealthCareContract.HealthCareServiceOperationEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceOperationEntry.buildHealthCareServiceOperationUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            /**
            case HEALTHCARE_SERVICE_DOCTORS: {
                long _id = db.insert(HealthCareContract.HealthCareServiceDoctorEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceDoctorEntry.buildHealthCareServiceDoctorUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES: {
                long _id = db.insert(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceDoctorSpecialityEntry.buildHealthCareServiceDoctorSpecialityUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS: {
                long _id = db.insert(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = HealthCareContract.HealthCareServiceDoctorTimeslotEntry.buildHealthCareServiceDoctorTimeslotUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            /**/
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
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_FAX, HEALTHCARE_SERVICE_FAX);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_TAGS, HEALTHCARE_SERVICE_TAGS);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_OPERATIONS, HEALTHCARE_SERVICE_OPERATIONS);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_DOCTORS, HEALTHCARE_SERVICE_DOCTORS);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_DOCTOR_SPECIALITIES, HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES);
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH_HEALTHCARE_SERVICE_DOCTOR_TIMESLOTS, HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS);
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
            case HEALTHCARE_SERVICE_FAX:
                return HealthCareContract.HealthCareServiceFaxEntry.TABLE_NAME;
            case HEALTHCARE_SERVICE_TAGS:
                return HealthCareContract.HealthCareServiceTagEntry.TABLE_NAME;
            case HEALTHCARE_SERVICE_OPERATIONS:
                return HealthCareContract.HealthCareServiceOperationEntry.TABLE_NAME;
            /**
            case HEALTHCARE_SERVICE_DOCTORS:
                return HealthCareContract.HealthCareServiceDoctorEntry.TABLE_NAME;
            case HEALTHCARE_SERVICE_DOCTORS_SPECIALITIES:
                return HealthCareContract.HealthCareServiceDoctorSpecialityEntry.TABLE_NAME;
            case HEALTHCARE_SERVICE_DOCTORS_TIMESLOTS:
                return HealthCareContract.HealthCareServiceDoctorTimeslotEntry.TABLE_NAME;
            /**/
            case HEALTHCARE_INFOS:
                return HealthCareContract.HealthCareInfoEntry.TABLE_NAME;
            case HEALTHCARE_INFO_AUTHORS:
                return HealthCareContract.HealthCareInfoAuthorEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
