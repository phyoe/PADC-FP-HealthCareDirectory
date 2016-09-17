package com.padc.healthcaredirectory.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.models.HealthCareModel;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_favourite)
    FloatingActionButton fabFavourite;

    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;

    @BindView(R.id.img_hospital)
    ImageView imgHospital;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    private HealthCareVO mHealthCare;
    private int mHospitalId;

    private static final String IE_HOSPITAL_ID = "IE_HOSPITAL_ID";

    public static Intent newIntent(int healthcareId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), HospitalDetailActivity.class);
        intent.putExtra(IE_HOSPITAL_ID, healthcareId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mHospitalId = getIntent().getIntExtra(IE_HOSPITAL_ID, 0);
        mHealthCare = HealthCareModel.getInstance().getHealthCareById(mHospitalId);

        tvHospitalName.setText(mHealthCare.getName());
        collapsingToolbar.setTitle(mHealthCare.getName());

        Glide.with(imgHospital.getContext())
                .load(R.drawable.asia_royal_hospital)
                .fitCenter()
                .placeholder(R.drawable.asia_royal_hospital)
                .error(R.drawable.asia_royal_hospital)
                .into(imgHospital);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = HealthCareDirectoryApp.getContext();
            String transitionName = context.getResources().getString(R.string.healthcare_list_detail_transition_name);
            imgHospital.setTransitionName(transitionName);
        }

    }

}
