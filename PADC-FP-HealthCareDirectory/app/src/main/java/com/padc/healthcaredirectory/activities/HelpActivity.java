package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.HelpViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager_help)
    ViewPager pagerHelp;

    PagerAdapter mPagerAdapter;
    int[] screenShots;

    public static Intent newIntent(){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), HelpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        screenShots = new int[] { R.drawable.healthcare01,
                R.drawable.healthcare02, R.drawable.healthcare03,
                R.drawable.healthcare04, R.drawable.healthcare05 };

        // Pass results to ViewPagerAdapter Class
        mPagerAdapter = new HelpViewPagerAdapter(this, screenShots);
        // Binds the Adapter to the ViewPager
        pagerHelp.setAdapter(mPagerAdapter);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
