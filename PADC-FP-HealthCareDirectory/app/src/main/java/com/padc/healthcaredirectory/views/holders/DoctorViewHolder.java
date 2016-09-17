package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.ArticleVO;
import com.padc.healthcaredirectory.data.vos.DoctorVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @BindView(R.id.tv_name)
    TextView tvName;

    private ControllerDocotorItem mController;
    private DoctorVO mDoctor;

    public DoctorViewHolder(View itemView, ControllerDocotorItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(DoctorVO doctor) {
        mDoctor = doctor;

        Log.d(HealthCareDirectoryApp.TAG, doctor.getName());

        tvName.setText(doctor.getName());

        int img = R.drawable.dummy_doctor;
        Glide.with(ivIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.dummy_doctor)
                .error(R.drawable.dummy_doctor)
                .into(ivIcon);
    }

    @Override
    public void onClick(View v) {

    }

    public interface ControllerDocotorItem {
        void onTapDoctor(DoctorVO doctor);
    }
}
