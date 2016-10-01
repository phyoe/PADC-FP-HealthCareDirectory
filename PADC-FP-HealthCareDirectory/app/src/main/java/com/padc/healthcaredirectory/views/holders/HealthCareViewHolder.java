package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_healthcare)
    ImageView ivHealthCare;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private ControllerHealthCareItem mController;
    private HealthCareVO mHealthCare;

    public HealthCareViewHolder(View itemView, ControllerHealthCareItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(HealthCareVO healthCare) {
        mHealthCare = healthCare;
        String phones = "";

        Log.d(HealthCareDirectoryApp.TAG, Arrays.toString(healthCare.getPhones()));

        if(healthCare.getPhones() != null){
            String[] arr_phones = healthCare.getPhones();
            for (String ph : arr_phones) {
                phones = phones + ph + ", ";
            }
        } else {
            throw new NullPointerException("Data is empty.");
        }
        tvName.setText(healthCare.getName());
        tvAddress.setText(healthCare.getAddress());
        tvPhone.setText(phones);

        String category = healthCare.getCategory();
        int img = R.drawable.dummy_healthcare;
        if(category.contains(HealthCareDirectoryConstants.STR_HOSPITAL)) img = R.drawable.asia_royal_hospital;
        if(category == HealthCareDirectoryConstants.STR_CLINIC) img = R.drawable.dummy_clinic;
        if(category == HealthCareDirectoryConstants.STR_PHARMACY) img = R.drawable.dummy_pharmacy;

        Glide.with(ivHealthCare.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.healthcare_photo_placeholder)
                .error(R.drawable.healthcare_photo_placeholder)
                .into(ivHealthCare);
    }

    @Override
    public void onClick(View view) {
        mController.onTapHealthCare(mHealthCare, ivHealthCare);
    }

    public interface ControllerHealthCareItem {
        void onTapPhoneCall(HealthCareVO healthcare);
        void onTapHealthCare(HealthCareVO healthcare, ImageView ivHealthCare);
    }

    @OnClick(R.id.btn_phone_call)
    public void onTapPhoneCall(View view) {
        mController.onTapPhoneCall(mHealthCare);
    }

    /**
    @OnClick(R.id.btn_detail)
    public void onTapHealthCare(View view) {
        mController.onTapHealthCare(mHealthCare, ivHealthCare);
    }
    /**/
}
