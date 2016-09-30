package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.fragments.VeterinaryClinicListFragment;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.VeterinaryClinicViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeterinaryClinicListActivity extends AppCompatActivity implements VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem{

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
            VeterinaryClinicListFragment fragment = VeterinaryClinicListFragment.newInstance(mcurrentClick);
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
    public void onTapVeterinaryClinic(VeterinaryClinicVO veterinaryClinicVO, String currentClick) {
        long id = 0001;

        mcurrentClick=currentClick;

        Intent intent = VeterinaryClinicDetailActivity.newIntent(id);

        switch (mcurrentClick)
        {
            case HealthCareDirectoryConstants.STR_VET_CLINIC:
                intent = VeterinaryClinicDetailActivity.newIntent(id);
                break;
            case HealthCareDirectoryConstants.STR_VET_EQUIPMENT:
                intent = VeterinaryEquipmentDetailActivity.newIntent(id);
                break;
            case HealthCareDirectoryConstants.STR_VET_MEDICINE:
                intent = VeterinaryMedicineDetailActivity.newIntent(id);
                break;
            case HealthCareDirectoryConstants.STR_VET_SPA:
                intent = VeterinarySpaDetailActivity.newIntent(id);
                break;

        }

        startActivity(intent);
    }
}
