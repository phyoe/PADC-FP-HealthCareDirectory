package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DoctorCategoryVO;
import com.padc.healthcaredirectory.data.vos.DoctorVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_name)
    TextView tvName;

    private ControllerDocotorCategoryItem mController;
    private DoctorVO mDoctor;
    private DoctorCategoryVO mDoctorCategory;

    public DoctorCategoryViewHolder(View itemView, ControllerDocotorCategoryItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(DoctorCategoryVO doctorCategory) {
        mDoctorCategory = doctorCategory;

        Log.d(HealthCareDirectoryApp.TAG, doctorCategory.getCategoryMM());

        tvName.setText(doctorCategory.getCategoryMM());

    }

    public void bindData(DoctorVO doctor) {
        mDoctor = doctor;

        Log.d(HealthCareDirectoryApp.TAG, doctor.getCategoryMM());

        tvName.setText(doctor.getCategoryMM());

    }

    @Override
    public void onClick(View v) {
        mController.onTapDoctorCategory(mDoctor);
    }

    public interface ControllerDocotorCategoryItem {
        void onTapDoctorCategory(DoctorVO doctor);
    }
}
