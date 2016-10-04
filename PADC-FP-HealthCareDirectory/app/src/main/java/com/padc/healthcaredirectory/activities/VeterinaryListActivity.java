package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.PhoneVO;
import com.padc.healthcaredirectory.fragments.VeterinaryListFragment;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeterinaryListActivity extends BaseActivity
        implements HealthCareServiceViewHolder.ControllerHealthCareItem{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), VeterinaryListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            VeterinaryListFragment fragment = VeterinaryListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onTapPhoneCall(HealthCareServiceVO healthCare) {
        List<PhoneVO> healthCarePhones = healthCare.getPhones();
        String numberToCall = "";
        if(healthCarePhones != null && !healthCarePhones.isEmpty()){
            numberToCall = healthCarePhones.get(0).getPhoneName();
            super.makeCall(numberToCall);
        } else {
            Toast.makeText(HealthCareDirectoryApp.getContext(), getString(R.string.str_no_phone_number), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTapHealthCareService(HealthCareServiceVO healthcare, ImageView ivHealthCare) {
        long id = healthcare.getHealthCareId();
        String name = healthcare.getHealthCareName();
        String loadedCategory = healthcare.getCategory();

        Intent intent = null;

        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_VET)) {
            intent = VeterinaryDetailActivity.newIntent(id);
            startActivity(intent);
        }
    }
}
