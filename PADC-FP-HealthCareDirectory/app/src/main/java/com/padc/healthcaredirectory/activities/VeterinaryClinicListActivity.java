package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.fragments.VeterinaryClinicListFragment;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;
import com.padc.healthcaredirectory.views.holders.VeterinaryClinicViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeterinaryClinicListActivity extends AppCompatActivity implements HealthCareServiceViewHolder.ControllerHealthCareItem{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    static String mcurrentClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_clinic_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            VeterinaryClinicListFragment fragment = VeterinaryClinicListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_veterinary_clinic_container, fragment)
                    .commit();
        }
    }

    public static Intent newIntent() {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), VeterinaryClinicListActivity.class);
        return intent;
    }

    public static Intent newIntent(String currentClick) {

        mcurrentClick=currentClick;
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), VeterinaryClinicListActivity.class);
        return intent;
    }



    @Override
    public void onTapPhoneCall(HealthCareServiceVO healthcare) {

    }

    @Override
    public void onTapHealthCareService(HealthCareServiceVO healthcare, ImageView ivHealthCare) {
        long id = healthcare.getHealthCareId();
        String name = healthcare.getHealthCareName();
        String loadedCategory = healthcare.getCategory();

        Intent intent = null;

        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_HOSPITAL)) {
            intent = HospitalDetailActivity.newIntent(id);
            startActivity(intent);
        }
        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_CLINIC)) {
            intent = ClinicDetailActivity.newIntent(name);
            startActivity(intent);
        }
        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_PHARMACY)) {
            intent = PhamacyDetailActivity.newIntent(name);
            startActivity(intent);
        }
    }
}
