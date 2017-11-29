package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_healthcare)
    ImageView ivHealthCare;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_category)
    TextView tvCategory;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    private ControllerHealthCareItem mController;
    private HealthCareServiceVO mHealthCareService;

    public HealthCareServiceViewHolder(View itemView, ControllerHealthCareItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(HealthCareServiceVO healthCareService) {
        mHealthCareService = healthCareService;

        //For Retrieve from Offline Json
        /**
         String phones = "";
         if(healthCare.getPhones() != null){
         String[] arr_phones = healthCare.getPhones();
         for (String ph : arr_phones) {
         phones = phones + ph + ", ";
         }
         } else {
         throw new NullPointerException("Data is empty.");
         }

         String category = healthCare.getCategory();
         int img = R.drawable.dummy_healthcare;
         if(category.contains(HealthCareDirectoryConstants.STR_HOSPITAL)) img = R.drawable.asia_royal_hospital;
         if(category == HealthCareDirectoryConstants.STR_CLINIC) img = R.drawable.dummy_clinic;
         if(category == HealthCareDirectoryConstants.STR_PHARMACY) img = R.drawable.dummy_pharmacy;
         /**/

        tvName.setText(healthCareService.getHealthCareName());

        String category_mm = healthCareService.getCategoryMM();
        String tags = "";
        for(int i=0 ; i < healthCareService.getTags().size() ; i++) {
            tags = tags + "(" + healthCareService.getTags().get(i).getTagNameMM() + ") ";
        }
        tvCategory.setText(category_mm + "" + tags);

        tvAddress.setText(healthCareService.getAddress());

        String img = healthCareService.getImage();
        Glide.with(ivHealthCare.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.healthcare_photo_placeholder)
                .error(R.drawable.healthcare_photo_placeholder)
                .into(ivHealthCare);
    }

    @Override
    public void onClick(View view) {
        mController.onTapHealthCareService(mHealthCareService, ivHealthCare);
    }

    public interface ControllerHealthCareItem {
        void onTapPhoneCall(HealthCareServiceVO healthcare);
        void onTapHealthCareService(HealthCareServiceVO healthcare, ImageView ivHealthCare);
    }

    @OnClick(R.id.btn_phone_call)
    public void onTapPhoneCall(View view) {
        mController.onTapPhoneCall(mHealthCareService);
    }

    /**
     @OnClick(R.id.btn_detail)
     public void onTapHealthCare(View view) {
     mController.onTapHealthCareService(mHealthCareService, ivHealthCare);
     }
     /**/

}
