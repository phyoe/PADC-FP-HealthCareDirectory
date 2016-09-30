package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.models.DoctorModel;
import com.padc.healthcaredirectory.data.vos.DoctorVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_doctor)
    ImageView imgDoctor;

    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;

    @BindView(R.id.tv_doctor_qualifications)
    TextView tvDoctorQualifications;

    @BindView(R.id.tv_doctor_email)
    TextView tvDoctorEmail;

    @BindView(R.id.tv_doctor_website)
    TextView tvDoctorWebsite;

    @BindView(R.id.tv_doctor_facebook)
    TextView tvDoctorFacebook;

    private DoctorVO mDoctor;
    private int mDoctorId;

    private static final String IE_DOCTOR_ID = "IE_DOCTOR_ID";

    public static Intent newIntent(long doctorId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), DoctorDetailActivity.class);
        intent.putExtra(IE_DOCTOR_ID, doctorId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDoctorId = getIntent().getIntExtra(IE_DOCTOR_ID, 0);
        mDoctor = DoctorModel.getInstance().getDoctorById(mDoctorId);

        tvDoctorName.setText(mDoctor.getName());
        toolbar.setTitle(mDoctor.getName());
        tvDoctorQualifications.setText(mDoctor.getQualifications());
        tvDoctorEmail.setText(mDoctor.getEmail());
        tvDoctorWebsite.setText(mDoctor.getWebsite());
        tvDoctorFacebook.setText(mDoctor.getFacebook());

        Glide.with(imgDoctor.getContext())
                .load(R.drawable.dummy_doctor)
                .fitCenter()
                .placeholder(R.drawable.dummy_doctor)
                .error(R.drawable.dummy_doctor)
                .into(imgDoctor);
    }
}
