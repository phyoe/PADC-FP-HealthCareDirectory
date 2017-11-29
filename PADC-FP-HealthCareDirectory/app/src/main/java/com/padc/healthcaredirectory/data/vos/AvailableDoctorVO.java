package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class AvailableDoctorVO {

    @SerializedName("doctor-id")
    private long doctorId;

    @SerializedName("doctor-name")
    private String doctorName;

    @SerializedName("title")
    private String title;

    @SerializedName("speciality")
    private SpecialityVO speciality;

    @SerializedName("time-slots")
    private ArrayList<TimeSlotVO> timeSlots;

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpecialityVO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityVO speciality) {
        this.speciality = speciality;
    }

    public ArrayList<TimeSlotVO> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlotVO> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
