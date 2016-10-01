package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DiseaseVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class DiseaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_disease_icon)
    ImageView ivDiseaseIcon;

    @BindView(R.id.tv_disease_title)
    TextView tvDiseaseTitle;

    @BindView(R.id.tv_disease_description)
    TextView tvDiseaseDescription;



    private ControllerDiseaseItem mController;
    private DiseaseVO mdisease;

    public DiseaseViewHolder(View itemView, ControllerDiseaseItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;

    }

    public void bindData(DiseaseVO disease) {
        mdisease = disease;

        //Log.d(HealthCareDirectoryApp.TAG, healthCareInfo.getTitle());

        tvDiseaseTitle.setText(disease.getName());
        tvDiseaseDescription.setText(disease.getDescription());

        int img = R.drawable.dummy_disease_picture;
        Glide.with(ivDiseaseIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.dummy_disease_picture)
                .error(R.drawable.dummy_disease_picture)
                .into(ivDiseaseIcon);
    }



    @Override
    public void onClick(View view) {
        mController.onTapDisease(mdisease);
    }

    public interface ControllerDiseaseItem {
        void onTapDisease(DiseaseVO diseaseVO);
    }
}
