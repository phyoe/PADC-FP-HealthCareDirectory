package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class VeterinaryClinicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_veterinary_clinic_icon)
    ImageView ivVeterinaryClinicIcon;

    @BindView(R.id.tv_veterinary_clinic_name)
    TextView tvVeterinaryClinicName;

    @BindView(R.id.tv_veterinary_clinic_address)
    TextView tvVeterinaryClinicAddress;

    private String mCurrentClick;

    private ControllerVeterinaryClinicItem mController;
    private VeterinaryClinicVO mVeterinaryClinicVo;

    public VeterinaryClinicViewHolder(View itemView, ControllerVeterinaryClinicItem controller, String currentClick) {
        super(itemView);
        mCurrentClick=currentClick;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mController = controller;
    }

    public void bindData(VeterinaryClinicVO veterinaryClinic) {
        mVeterinaryClinicVo = veterinaryClinic;

        //Log.d(HealthCareDirectoryApp.TAG, healthCareInfo.getTitle());

        tvVeterinaryClinicName.setText(veterinaryClinic.getName());
        tvVeterinaryClinicAddress.setText(veterinaryClinic.getAddress());


        switch (mCurrentClick)
        {
            case HealthCareDirectoryConstants.STR_VET_CLINIC:
                setVetClinicImg();
                break;
            case HealthCareDirectoryConstants.STR_VET_EQUIPMENT:
                setVetEquipmentImg();
                break;
            case HealthCareDirectoryConstants.STR_VET_MEDICINE:
                setVetMedicineImg();
                break;
            case HealthCareDirectoryConstants.STR_VET_SPA:
                setVetSpaImg();
                break;

        }

    }

    @Override
    public void onClick(View view) {
        mController.onTapVeterinaryClinic(mVeterinaryClinicVo,mCurrentClick);
    }

    public interface ControllerVeterinaryClinicItem {
        void onTapVeterinaryClinic(VeterinaryClinicVO veterinaryClinicVO, String currentClick);
    }

    protected void setVetClinicImg()
    {
        int img = R.drawable.veterinary_clinic2;
        Glide.with(ivVeterinaryClinicIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.veterinary_clinic2)
                .error(R.drawable.veterinary_clinic2)
                .into(ivVeterinaryClinicIcon);
    }

    protected void setVetEquipmentImg()
    {
        int img = R.drawable.veterinary_equipment_icon;
        Glide.with(ivVeterinaryClinicIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.veterinary_equipment_icon)
                .error(R.drawable.veterinary_equipment_icon)
                .into(ivVeterinaryClinicIcon);
    }

    protected void setVetMedicineImg()
    {
        int img = R.drawable.veterinary_medicine_shop_icon;
        Glide.with(ivVeterinaryClinicIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.veterinary_medicine_shop_icon)
                .error(R.drawable.veterinary_medicine_shop_icon)
                .into(ivVeterinaryClinicIcon);
    }

    protected void setVetSpaImg()
    {
        int img = R.drawable.veterinary_spa_icon;
        Glide.with(ivVeterinaryClinicIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.veterinary_spa_icon)
                .error(R.drawable.veterinary_spa_icon)
                .into(ivVeterinaryClinicIcon);
    }


}
