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

    @SerializedName("fax")
    private ArrayList<FaxVO> fax;

    @SerializedName("tags")
    private ArrayList<TagVO> tags;

    @SerializedName("operations")
    private ArrayList<OperationVO> operations;

    @SerializedName("available-doctors")
    private ArrayList<AvailableDoctorVO> doctors;
    /**
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
    /**
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
            //Bulk insert into child tables.
            long service_id = healthCareService.getHealthCareId();
            ArrayList<PhoneVO> phoneList = healthCareService.getPhones();
            ArrayList<FaxVO> faxList = healthCareService.getFax();
            ArrayList<TagVO> tagList = healthCareService.getTags();

            ArrayList<OperationVO> operationList = new ArrayList<>();
            if(healthCareService.getOperations() != null && !healthCareService.getOperations().isEmpty()){
                //operationList = healthCareService.getOperations();
            }
            ArrayList<AvailableDoctorVO> doctorList = healthCareService.getDoctors();

            HealthCareServiceVO.saveHealthCareServicePhones(service_id, phoneList);
            HealthCareServiceVO.saveHealthCareServiceFax(service_id, faxList);
            HealthCareServiceVO.saveHealthCareServiceTags(service_id, tagList);
            HealthCareServiceVO.saveHealthCareServiceOperations(service_id, operationList);
            //HealthCareServiceVO.saveHealthCareServiceDoctors(service_id, doctorList);
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

    /**
     * HealthCareServicePhones
     */
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

    /**
     * HealthCareServiceFax
     */
    private static void saveHealthCareServiceFax(long service_id, ArrayList<FaxVO> faxList) {
        ContentValues[] healthCareServiceFaxCVs = new ContentValues[faxList.size()];
        for (int index = 0; index < faxList.size(); index++) {
            FaxVO fax = faxList.get(index);

            ContentValues cv = new ContentValues();
            long fax_id = fax.getFaxId();
            String fax_name = fax.getFaxName();
            cv.put(HealthCareContract.HealthCareServiceFaxEntry.COLUMN_SERVICE_ID, service_id);
            cv.put(HealthCareContract.HealthCareServiceFaxEntry.COLUMN_FAX_ID, fax_id);
            cv.put(HealthCareContract.HealthCareServiceFaxEntry.COLUMN_FAX_NAME, fax_name);

            healthCareServiceFaxCVs[index] = cv;
        }

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceFaxEntry.CONTENT_URI, healthCareServiceFaxCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_fax table : " + insertCount);
    }
    public static ArrayList<FaxVO> loadHealthCareServiceFaxByServiceId(long service_id){
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<FaxVO> faxList = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareServiceFaxEntry.buildHealthCareServiceFaxUriWithServiceId(service_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long fax_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceFaxEntry.COLUMN_FAX_ID));
                String fax_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceFaxEntry.COLUMN_FAX_NAME));

                FaxVO fax = new FaxVO();
                fax.setFaxId(fax_id);
                fax.setFaxName(fax_name);
                faxList.add(fax);

            } while (cursor.moveToNext());
        }
        return faxList;
    }

    /**
     * HealthCareServiceTags
     */
    private static void saveHealthCareServiceTags(long service_id, ArrayList<TagVO> tagList) {
        ContentValues[] healthCareServiceTagCVs = new ContentValues[tagList.size()];
        for (int index = 0; index < tagList.size(); index++) {
            TagVO tag = tagList.get(index);

            ContentValues cv = new ContentValues();
            long tag_id = tag.getTagId();
            String tag_name = tag.getTagName();
            String tag_name_mm = tag.getTagNameMM();
            cv.put(HealthCareContract.HealthCareServiceTagEntry.COLUMN_SERVICE_ID, service_id);
            cv.put(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_ID, tag_id);
            cv.put(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_NAME, tag_name);
            cv.put(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_NAME_MM, tag_name_mm);

            healthCareServiceTagCVs[index] = cv;
        }

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceTagEntry.CONTENT_URI, healthCareServiceTagCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_tag table : " + insertCount);
    }
    public static ArrayList<TagVO> loadHealthCareServiceTagsByServiceId(long service_id){
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<TagVO> tagList = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareServiceTagEntry.buildHealthCareServiceTagUriWithServiceId(service_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long tag_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_ID));
                String tag_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_NAME));
                String tag_name_mm = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceTagEntry.COLUMN_TAG_NAME_MM));

                TagVO tag = new TagVO();
                tag.setTagId(tag_id);
                tag.setTagName(tag_name);
                tag.setTagNameMM(tag_name_mm);
                tagList.add(tag);

            } while (cursor.moveToNext());
        }
        return tagList;
    }

    /**
     * HealthCareServiceOperations
     */
    private static void saveHealthCareServiceOperations(long service_id, ArrayList<OperationVO> operationList) {
        ContentValues[] healthCareServiceOperationCVs = new ContentValues[operationList.size()];

        if( operationList != null && !operationList.isEmpty()){
            for (int index = 0; index < operationList.size(); index++) {
                OperationVO operation = operationList.get(index);

                ContentValues cv = new ContentValues();
                long operation_id = operation.getOperationId();
                String operation_name = operation.getOperationName();

                cv.put(HealthCareContract.HealthCareServiceOperationEntry.COLUMN_SERVICE_ID, service_id);
                cv.put(HealthCareContract.HealthCareServiceOperationEntry.COLUMN_OPERATION_ID, operation_id);
                cv.put(HealthCareContract.HealthCareServiceOperationEntry.COLUMN_OPERATION_NAME, operation_name);

                healthCareServiceOperationCVs[index] = cv;
            }

            Context context = HealthCareDirectoryApp.getContext();
            int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceOperationEntry.CONTENT_URI, healthCareServiceOperationCVs);

            Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_operation table : " + insertCount);
        }
    }
    public static ArrayList<OperationVO> loadHealthCareServiceOperationsByServiceId(long service_id){
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<OperationVO> operationList = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareServiceOperationEntry.buildHealthCareServiceOperationUriWithServiceId(service_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long operation_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceOperationEntry.COLUMN_OPERATION_ID));
                String operation_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceOperationEntry.COLUMN_OPERATION_NAME));

                OperationVO operation = new OperationVO();
                operation.setOperationId(operation_id);
                operation.setOperationName(operation_name);
                operationList.add(operation);

            } while (cursor.moveToNext());
        }
        return operationList;
    }

    /**
     * HealthCareServiceDoctors
     */
    /**
    private static void saveHealthCareServiceDoctors(long service_id, ArrayList<AvailableDoctorVO> doctorList) {
        ContentValues[] healthCareServiceDoctorCVs = new ContentValues[doctorList.size()];
        for (int index = 0; index < doctorList.size(); index++) {
            AvailableDoctorVO doctor = doctorList.get(index);

            ContentValues cv = new ContentValues();
            long doctor_id = doctor.getDoctorId();
            String doctor_name = doctor.getDoctorName();
            String doctor_title = doctor.getTitle();

            cv.put(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_SERVICE_ID, service_id);
            cv.put(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_ID, doctor_id);
            cv.put(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_NAME, doctor_name);
            cv.put(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_TITLE, doctor_title);

            //Bulk insert into child tables.
            SpecialityVO specialityList = doctor.getSpeciality();
            ArrayList<TimeSlotVO> timeSlotList = doctor.getTimeSlots();

            HealthCareServiceVO.saveHealthCareServiceDoctorSpeciality(service_id, doctor_id, specialityList);
            HealthCareServiceVO.saveHealthCareServiceDoctorTimeslots(service_id, doctor_id, timeSlotList);

            healthCareServiceDoctorCVs[index] = cv;
        }

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceDoctorEntry.CONTENT_URI, healthCareServiceDoctorCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_doctors table : " + insertCount);
    }
    public static ArrayList<AvailableDoctorVO> loadHealthCareServiceDoctorsByServiceId(long service_id){
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<AvailableDoctorVO> doctorList = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(HealthCareContract.HealthCareServiceDoctorEntry.buildHealthCareServiceDoctorUriWithServiceId(service_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long doctor_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_ID));
                String doctor_name = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_NAME));
                String doctor_title = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorEntry.COLUMN_DOCTOR_TITLE));

                AvailableDoctorVO doctors = new AvailableDoctorVO();
                doctors.setDoctorId(doctor_id);
                doctors.setDoctorName(doctor_name);
                doctors.setTitle(doctor_title);
                doctorList.add(doctors);

            } while (cursor.moveToNext());
        }
        return doctorList;
    }
     /**/

    /**
     * HealthCareServiceDoctorSpeciality
     */
    /**
    private static void saveHealthCareServiceDoctorSpeciality(long service_id, long doctor_id, SpecialityVO specialityList) {
        ContentValues[] specialityCVs = new ContentValues[1];

        ContentValues cv = new ContentValues();
        cv.put(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_ID, service_id);
        cv.put(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SERVICE_DOCTOR_ID, doctor_id);
        cv.put(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_ID, specialityList.getSpecialityId());
        cv.put(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_TITLE, specialityList.getSpecialityTitle());

        specialityCVs[0] = cv;

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.CONTENT_URI, specialityCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_doctor_specialities table : " + insertCount);

    }
    public static SpecialityVO loadHealthCareServiceDoctorSpecialityByServiceId(long service_id, long doctor_id){
        Context context = HealthCareDirectoryApp.getContext();
        SpecialityVO speciality = new SpecialityVO();

        Cursor cursor = context.getContentResolver()
                .query(HealthCareContract
                        .HealthCareServiceDoctorSpecialityEntry
                        .buildHealthCareServiceDoctorSpecialityUriWithServiceId(service_id, doctor_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long spe_id = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_ID));
                String spe_title = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorSpecialityEntry.COLUMN_SPECIALITY_TITLE));

                speciality.setSpecialityId(spe_id);
                speciality.setSpecialityTitle(spe_title);
            } while (cursor.moveToNext());
        }
        return speciality;
    }
     /**/

    /**
     * HealthCareServiceDoctorTimeslots
     */
    /**
    private static void saveHealthCareServiceDoctorTimeslots(long service_id, long doctor_id, ArrayList<TimeSlotVO> timeSlotsList) {
        ContentValues[] timeslotCVs = new ContentValues[timeSlotsList.size()];
        for (int index = 0; index < timeSlotsList.size(); index++) {
            TimeSlotVO timeSlot = timeSlotsList.get(index);

            ContentValues cv = new ContentValues();
            long timeSlot_id = timeSlot.getTimeSlotId();
            String timeSlot_time = timeSlot.getTime();
            String timeSlot_date = timeSlot.getDate();

            cv.put(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_ID, service_id);
            cv.put(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_SERVICE_DOCTOR_ID, service_id);
            cv.put(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_ID, timeSlot_id);
            cv.put(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_TIME, timeSlot_time);
            cv.put(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_DATE, timeSlot_date);

            timeslotCVs[index] = cv;
        }

        Context context = HealthCareDirectoryApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.CONTENT_URI, timeslotCVs);

        Log.d(HealthCareDirectoryApp.TAG, "Bulk inserted into healthcare_service_doctor_timeslot table : " + insertCount);

    }
    public static ArrayList<TimeSlotVO> loadHealthCareServiceDoctorTimeslotsByServiceId(long service_id, long doctor_id){
        Context context = HealthCareDirectoryApp.getContext();
        ArrayList<TimeSlotVO> timeSlotList = new ArrayList<>();

        Cursor cursor = context.getContentResolver()
                .query(HealthCareContract
                        .HealthCareServiceDoctorTimeslotEntry
                        .buildHealthCareServiceDoctorTimeslotUriWithServiceId(service_id, doctor_id),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                long timeSlotId = cursor.getLong(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_ID));
                String timeSlotTime = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_TIME));
                String timeSlotDate = cursor.getString(cursor.getColumnIndex(HealthCareContract.HealthCareServiceDoctorTimeslotEntry.COLUMN_TIMESLOT_DATE));

                TimeSlotVO timeSlot = new TimeSlotVO();
                timeSlot.setTimeSlotId(timeSlotId);
                timeSlot.setTime(timeSlotTime);
                timeSlot.setDate(timeSlotDate);
                timeSlotList.add(timeSlot);

            } while (cursor.moveToNext());
        }
        return timeSlotList;
    }
    /**/
}
