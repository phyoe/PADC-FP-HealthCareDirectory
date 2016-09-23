package com.padc.healthcaredirectory.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Phyoe Khant on 9/20/2016.
 */
public class AvailableDoctorVO {

    @SerializedName("doctor-id")
    private int doctorId;

    @SerializedName("doctor-name")
    private String doctorName;

    @SerializedName("title")
    private String title;

    @SerializedName("speciality")
    private ArrayList<SpecialityVO> speciality;

    @SerializedName("time-slots")
    private ArrayList<TimeSlotVO> timeSlots;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
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

    public ArrayList<SpecialityVO> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(ArrayList<SpecialityVO> speciality) {
        this.speciality = speciality;
    }

    public ArrayList<TimeSlotVO> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlotVO> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
