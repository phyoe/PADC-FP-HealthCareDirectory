package com.padc.healthcaredirectory.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/21/2016.
 */
public class HealthCareInfoVO {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("author")
    private AuthorVO author;

    @SerializedName("short-description")
    private String shortDescription;

    @SerializedName("published-date")
    private String publishedDate;

    @SerializedName("complete-url")
    private String completeUrl;

    @SerializedName("info-type")
    private String infoType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AuthorVO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorVO author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    /**
     * For Persistence Layer
     */
    public static void saveHealthCareInfo(List<HealthCareInfoVO> healthCareInfoList) {
        Context context = HealthCareDirectoryApp.getContext();
        ContentValues[] healthCareInfoCVs = new ContentValues[healthCareInfoList.size()];
        for (int index = 0; index < healthCareInfoList.size(); index++) {
            HealthCareInfoVO healthCareInfo = healthCareInfoList.get(index);
            healthCareInfoCVs[index] = healthCareInfo.parseToContentValues();

            //Bulk insert into author.
            long service_id = healthCareInfo.getId();
            long author_id = healthCareInfo.getAuthor().getAuthorId();
            String author_name = healthCareInfo.getAuthor().getAuthorName();
            String author_picture = healthCareInfo.getAuthor().getAuthorPicture();
            HealthCareInfoVO.saveHealthCareInfoAuthor(service_id, author_id, author_name, author_picture);
        }

        //Bulk insert into healthcare_info.
        int insertedCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareInfoEntry.CONTENT_URI, healthCareInfoCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into Helthcareinfo table : " + insertedCount);
    }
    private static void saveHealthCareInfoAuthor(long info_id, long author_id, String author_name, String author_picture) {
        ContentValues[] healthCareInfoAuthorCVs = new ContentValues[1];

        ContentValues cv = new ContentValues();
        cv.put(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_INFO_ID, info_id);
        cv.put(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_ID, author_id);
        cv.put(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_NAME, author_name);
        cv.put(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_PICTURE, author_picture);

        healthCareInfoAuthorCVs[0] = cv;

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareInfoAuthorEntry.CONTENT_URI, healthCareInfoAuthorCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_info_author table : " + insertCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_HEALTHCARE_INFO_ID, id);
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_TITLE, title);
        //cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_IMAGE, image);
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_SHORT_DESC, shortDescription);
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_PUBLISHED_DATE, publishedDate);
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_COMPLETE_URL, completeUrl);
        cv.put(HealthCareContract.HealthCareInfoEntry.COLUMN_INFO_TYPE, infoType);
        return cv;
    }

    public static HealthCareInfoVO parseFromCursor(Cursor data) {
        HealthCareInfoVO healthCareInfo = new HealthCareInfoVO();

        healthCareInfo.id = data.getInt(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_HEALTHCARE_INFO_ID));
        healthCareInfo.title = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_TITLE));
        //healthCareInfo.image = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_IMAGE));
        healthCareInfo.shortDescription = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_SHORT_DESC));
        healthCareInfo.publishedDate = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_PUBLISHED_DATE));
        healthCareInfo.completeUrl = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_COMPLETE_URL));
        healthCareInfo.infoType = data.getString(data.getColumnIndex(HealthCareContract.HealthCareInfoEntry.COLUMN_INFO_TYPE));

        return healthCareInfo;
    }

    public static AuthorVO loadHealthCareInfoAuthorByInfoId(long info_id) {
        Context context = HealthCareDirectoryApp.getContext();
        AuthorVO author = new AuthorVO();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareInfoAuthorEntry.buildHealthCareInfoAuthorUriWithInfoId(info_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long author_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_ID));
                String author_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_NAME));
                String author_picture = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareInfoAuthorEntry.COLUMN_AUTHOR_PICTURE));

                author.setAuthorId(author_id);
                author.setAuthorName(author_name);
                author.setAuthorPicture(author_picture);
            } while (cursor.moveToNext());
        }
        return author;
    }
}
