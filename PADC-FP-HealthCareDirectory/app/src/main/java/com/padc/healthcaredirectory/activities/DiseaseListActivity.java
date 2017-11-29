package com.padc.healthcaredirectory.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.fragments.DiseaseListFragment;
import com.padc.healthcaredirectory.views.holders.HealthCareInfoViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiseaseListActivity extends AppCompatActivity implements HealthCareInfoViewHolder.ControllerHealthCareInfoItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent() {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), DiseaseListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            DiseaseListFragment fragment = DiseaseListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_disease_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onTapHealthCareInfo(HealthCareInfoVO healthCareInfo) {

        long id = healthCareInfo.getId();

        /**
         Intent intent = ArticleDetailActivity.newIntent(id);
         startActivity(intent);
         /**/

        Intent intent = ArticleDetailWebViewActivity.newIntent(id);
        startActivity(intent);
    }
}
