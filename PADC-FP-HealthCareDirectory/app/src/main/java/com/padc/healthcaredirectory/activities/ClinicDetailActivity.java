package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClinicDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_clinic_name_label)
    TextView tvClinicName;

    private String mClinicTitle;

    private static final String IE_CLINIC_NAME = "IE_CLINIC_NAME";

    public static Intent newIntent(String healthcareName) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), ClinicDetailActivity.class);
        intent.putExtra(IE_CLINIC_NAME, healthcareName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_detail);
        ButterKnife.bind(this, this);

        mClinicTitle = getIntent().getStringExtra(IE_CLINIC_NAME);
        tvClinicName.setText(mClinicTitle);
    }
}
