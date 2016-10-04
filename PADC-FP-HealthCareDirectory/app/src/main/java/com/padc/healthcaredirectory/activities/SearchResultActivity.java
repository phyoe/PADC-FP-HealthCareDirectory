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
import com.padc.healthcaredirectory.fragments.SearchResultFragment;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActivity
        implements HealthCareServiceViewHolder.ControllerHealthCareItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String mSearchText;
    private static final String IE_SEARCH = "IE_SEARCH";

    public static Intent newInent(String searchText){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), SearchResultActivity.class);
        intent.putExtra(IE_SEARCH, searchText);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSearchText = getIntent().getStringExtra(IE_SEARCH);
        
        if (savedInstanceState == null) {
            SearchResultFragment fragment = SearchResultFragment.newInstance();
            Bundle data = new Bundle();//Use bundle to pass data
            data.putString("searchText", mSearchText);//put string, int, etc in bundle with
            fragment.setArguments(data);//Finally set argument bundle to fragment

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

        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_HOSPITAL)) {
            intent = HospitalDetailActivity.newIntent(id);
            startActivity(intent);
        }
        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_CLINIC)) {
            intent = ClinicDetailActivity.newIntent(id);
            startActivity(intent);
        }
        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_PHARMACY)) {
            intent = PhamacyDetailActivity.newIntent(id);
            startActivity(intent);
        }
        if(loadedCategory.contains(HealthCareDirectoryConstants.STR_VET)) {
            intent = VeterinaryDetailActivity.newIntent(id);
            startActivity(intent);
        }
    }
}
