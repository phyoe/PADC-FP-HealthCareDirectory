package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_hospital_name_label)
    TextView tvHealthcareName;

    private String mHealthcareTitle;

    private static final String IE_HEALTHCARE_NAME = "IE_HEALTHCARE_NAME";

    public static Intent newIntent(String healthcareName) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), HospitalDetailActivity.class);
        intent.putExtra(IE_HEALTHCARE_NAME, healthcareName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);
        ButterKnife.bind(this, this);

        mHealthcareTitle = getIntent().getStringExtra(IE_HEALTHCARE_NAME);
        tvHealthcareName.setText(mHealthcareTitle);

    }
}
