package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhamacyDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_phamacy_name_label)
    TextView tvPharmacyName;

    private String mPharmacyTitle;

    private static final String IE_PHARMACY_NAME = "IE_PHARMACY_NAME";

    public static Intent newIntent(String healthcareName) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), PhamacyDetailActivity.class);
        intent.putExtra(IE_PHARMACY_NAME, healthcareName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phamacy_detail);
        ButterKnife.bind(this, this);

        mPharmacyTitle = getIntent().getStringExtra(IE_PHARMACY_NAME);
        tvPharmacyName.setText(mPharmacyTitle);
    }
}
