package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeterinaryCategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rl_clinic)
    RelativeLayout rlVeterinaryClinic;

    @BindView(R.id.rl_equipment)
    RelativeLayout rlVeterinaryEquipment;

    @BindView(R.id.rl_medicine)
    RelativeLayout rlVeterinaryMedicine;

    @BindView(R.id.rl_spa)
    RelativeLayout rlVeterinarySpa;

    String currentClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_category);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        rlVeterinaryClinic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                currentClick= HealthCareDirectoryConstants.STR_VET_CLINIC;
                Intent intent = VeterinaryClinicListActivity.newIntent(currentClick);
                startActivity(intent);
            }
        });

        rlVeterinaryEquipment.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                currentClick= HealthCareDirectoryConstants.STR_VET_EQUIPMENT;
                Intent intent = VeterinaryClinicListActivity.newIntent(currentClick);
                startActivity(intent);
            }
        });

        rlVeterinaryMedicine.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                currentClick= HealthCareDirectoryConstants.STR_VET_MEDICINE;
                Intent intent = VeterinaryClinicListActivity.newIntent(currentClick);
                startActivity(intent);
            }
        });

        rlVeterinarySpa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                currentClick= HealthCareDirectoryConstants.STR_VET_SPA;
                Intent intent = VeterinaryClinicListActivity.newIntent(currentClick);
                startActivity(intent);
            }
        });
    }

    public static Intent newIntent() {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), VeterinaryCategoryActivity.class);
        return intent;
    }
}
