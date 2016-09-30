package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeterinaryClinicDetailActivity extends AppCompatActivity {

    private static final String IE_INFO_ID = "IE_INFO_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_clinic_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent newIntent(long infoId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), VeterinaryClinicDetailActivity.class);
        intent.putExtra(IE_INFO_ID, infoId);
        return intent;
    }
}
