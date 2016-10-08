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

        screenShots = new int[] {
                R.drawable.help01,
                R.drawable.help02, R.drawable.help02i, R.drawable.help02ii,
                R.drawable.help03, R.drawable.help03i, R.drawable.help03ii,
                R.drawable.help04, R.drawable.help04i, R.drawable.help04ii,
                R.drawable.help06, R.drawable.help06i, R.drawable.help06ii,
                R.drawable.help07, R.drawable.help08, R.drawable.help09,
                R.drawable.help10, R.drawable.help11, R.drawable.help12 };

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
