package com.padc.healthcaredirectory.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class HealthCareServiceVO {

    @SerializedName("health-care-id")
    private long healthCareId;

    @SerializedName("health-care-name")
    private String healthCareName;

    @SerializedName("category")
    private String category;

    @SerializedName("category-mm")
    private String categoryMM;

    @SerializedName("image")
    private String image;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName("website")
    private String website;

    @SerializedName("mapinfo")
    private String mapinfo;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("remark")
    private String remark;

    @SerializedName("price-category")
    private int priceCategory;

    @SerializedName("phones")
    private ArrayList<PhoneVO> phones;
    /**
    @SerializedName("fax")
    private ArrayList<FaxVO> fax;

    @SerializedName("tags")
    private ArrayList<TagVO> tags;

    @SerializedName("operations")
    private ArrayList<OperationVO> operations;


    @SerializedName("available-doctors")
    private ArrayList<AvailableDoctorVO> doctors;
    /**/

    public long getHealthCareId() {
        return healthCareId;
    }

    public void setHealthCareId(long healthCareId) {
        this.healthCareId = healthCareId;
    }

    public String getHealthCareName() {
        return healthCareName;
    }

    public void setHealthCareName(String healthCareName) {
        this.healthCareName = healthCareName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryMM() {
        return categoryMM;
    }

    public void setCategoryMM(String categoryMM) {
        this.categoryMM = categoryMM;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMapinfo() {
        return mapinfo;
    }

    public void setMapinfo(String mapinfo) {
        this.mapinfo = mapinfo;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }


    public ArrayList<PhoneVO> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<PhoneVO> phones) {
        this.phones = phones;
    }
    /**
    public ArrayList<FaxVO> getFax() {
        return fax;
    }

    public void setFax(ArrayList<FaxVO> fax) {
        this.fax = fax;
    }

    public ArrayList<TagVO> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagVO> tags) {
        this.tags = tags;
    }

    public ArrayList<OperationVO> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<OperationVO> operations) {
        this.operations = operations;
    }

    public ArrayList<AvailableDoctorVO> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<AvailableDoctorVO> doctors) {
        this.doctors = doctors;
    }
    /**/

    /**
     * For Persistence Layer
     */
    public static void saveHealthCareService(List<HealthCareServiceVO> healthCareServiceList) {
        Context context = HealthCareDirectoryApp.getContext();
        ContentValues[] healthCareServiceCVs = new ContentValues[healthCareServiceList.size()];
        for (int index = 0; index < healthCareServiceList.size(); index++) {
            HealthCareServiceVO healthCareService = healthCareServiceList.get(index);
            healthCareServiceCVs[index] = healthCareService.parseToContentValues();

            /**/
            //Bulk insert into phones.
            long service_id = healthCareService.getHealthCareId();
            ArrayList<PhoneVO> phoneList = healthCareService.getPhones();
            HealthCareServiceVO.saveHealthCareServicePhones(service_id, phoneList);
            /**/
        }

        //Bulk insert into healthcare_service.
        int insertedCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceEntry.CONTENT_URI, healthCareServiceCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into HelthcareService table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_ID, healthCareId);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME, healthCareName);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_CATEGORY, category);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_CATEGORY_MM, categoryMM);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_IMAGE, image);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_ADDRESS, address);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_EMAIL, email);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_WEBSITE, website);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_MAPINFO, mapinfo);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_FACEBOOK, facebook);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_REMARK, remark);
        cv.put(HealthCareContract.HealthCareServiceEntry.COLUMN_PRICE_CATEGORY, priceCategory);
        return cv;
    }

    public static HealthCareServiceVO parseFromCursor(Cursor data) {
        HealthCareServiceVO healthCareService = new HealthCareServiceVO();

        healthCareService.healthCareId      = data.getLong(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_ID));
        healthCareService.healthCareName    = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME));
        healthCareService.category          = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_CATEGORY));
        healthCareService.categoryMM        = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_CATEGORY_MM));
        healthCareService.image             = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_IMAGE));
        healthCareService.address           = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_ADDRESS));
        healthCareService.email             = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_EMAIL));
        healthCareService.website           = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_WEBSITE));
        healthCareService.mapinfo           = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_MAPINFO));
        healthCareService.facebook          = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_FACEBOOK));
        healthCareService.remark            = data.getString(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_REMARK));
        healthCareService.priceCategory     = data.getInt(data.getColumnIndex(HealthCareContract.HealthCareServiceEntry.COLUMN_PRICE_CATEGORY));

        return healthCareService;
    }

    private static void saveHealthCareServicePhones(long service_id, ArrayList<PhoneVO> phoneList) {
        ContentValues[] healthCareServicePhoneCVs = new ContentValues[phoneList.size()];
        for (int index = 0; index < phoneList.size(); index++) {
            PhoneVO phone = phoneList.get(index);

            ContentValues cv = new ContentValues();
            long phone_id = phone.getPhoneId();
            String phone_name = phone.getPhoneName();
            cv.put(HealthCareContract.HealthCareServicePhoneEntry.COLUMN_SERVICE_ID, service_id);
            cv.put(HealthCareContract.HealthCareServicePhoneEntry.COLUMN_PHONE_ID, phone_id);
            cv.put(HealthCareContract.HealthCareServicePhoneEntry.COLUMN_PHONE_NAME, phone_name);

            healthCareServicePhoneCVs[index] = cv;
        }

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServicePhoneEntry.CONTENT_URI, healthCareServicePhoneCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_phone table : " + insertCount);
    }

    public static ArrayList<PhoneVO> loadHealthCareServicePhoneByServiceId(long service_id) {
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<PhoneVO> phoneList = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareServicePhoneEntry.buildHealthCareServicePhoneUriWithServiceId(service_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long phone_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServicePhoneEntry.COLUMN_PHONE_ID));
                String phone_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServicePhoneEntry.COLUMN_PHONE_NAME));

                PhoneVO phone = new PhoneVO();
                phone.setPhoneId(phone_id);
                phone.setPhoneName(phone_name);
                phoneList.add(phone);

            } while (cursor.moveToNext());
        }
        return phoneList;
    }
}
