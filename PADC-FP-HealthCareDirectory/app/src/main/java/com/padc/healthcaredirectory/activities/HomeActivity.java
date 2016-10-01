package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.fragments.HealthCarePagerFragment;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.utils.MMFontUtils;
import com.padc.healthcaredirectory.views.holders.DoctorCategoryViewHolder;
import com.padc.healthcaredirectory.views.holders.DoctorViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareInfoViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HealthCareViewHolder.ControllerHealthCareItem,
        DoctorCategoryViewHolder.ControllerDocotorCategoryItem,
        DoctorViewHolder.ControllerDocotorItem,
        HealthCareServiceViewHolder.ControllerHealthCareItem,
        HealthCareInfoViewHolder.ControllerHealthCareInfoItem {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.fab_search)
    FloatingActionButton fabSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_healthcare_launcher_icon);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);

        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);
        navigationView.setNavigationItemSelectedListener(this);

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (savedInstanceState == null) {
            navigateToTabLayout();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        fabSearch.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {
            case R.id.menu_health_care_clinics:
                navigateToClinicList();
                return true;
            /**
             case R.id.menu_health_care_doctors:
                navigateToDoctorList();
                return true;
            /**/
            case R.id.menu_health_care_veterinary:
                navigateToVeterinaryList();
                return true;
            case R.id.menu_health_care_diseases:
                navigateToDiseaseList();
                return true;
            case R.id.menu_health_care_articles:
                navigateToArticleList();
                return true;
            case R.id.menu_favourite_list:
                navigateToFavList();
                return true;
            case R.id.menu_help:
                navigateToHelp();
                return true;
            case R.id.menu_about_us:
                navigateToAboutUs();
                return true;
        }
        return false;
    }

    private void navigateToTabLayout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, HealthCarePagerFragment.newInstance())
                .commit();
    }

    private void navigateToClinicList() {

    }

    private void navigateToDoctorList() {
        //Toast.makeText(HealthCareDirectoryApp.getContext(), "Doctor will show ...", Toast.LENGTH_SHORT).show();

        Intent intent = DoctorCategoryListActivity.newIntent();
        startActivity(intent);
    }

    private void navigateToVeterinaryList() {

    }

    private void navigateToDiseaseList() {

    }

    private void navigateToFavList() {

    }

    private void navigateToHelp() {

    }

    private void navigateToArticleList() {
       //Toast.makeText(HealthCareDirectoryApp.getContext(), "Article will show ...", Toast.LENGTH_SHORT).show();

        Intent inent = ArticleListActivity.newInent();
        startActivity(inent);
    }

    private void navigateToAboutUs() {

    }

    @Override
    public void onTapPhoneCall(HealthCareVO healthcare) {
        if(healthcare.getPhones()[0] != null) {
            String numberToCall = healthcare.getPhones()[0];
            makeCall(numberToCall);
        }
    }

    @Override
    public void onTapHealthCare(HealthCareVO healthcare, ImageView ivHealthCare) {

        //Toast.makeText(getApplicationContext(), "Detail View will show ...", Toast.LENGTH_SHORT).show();

        long id = healthcare.getId();
        String name = healthcare.getName();
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

    @Override
    public void onTapDoctor(DoctorVO doctor) {

    }

    @Override
    public void onTapDoctorCategory(DoctorVO doctor) {

    }

    @Override
    public void onTapPhoneCall(HealthCareServiceVO healthcare) {
        /**
        if(healthcare.getPhones()[0] != null) {
            String numberToCall = healthcare.getPhones()[0];
            makeCall(numberToCall);
        }
        /**/
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

    @Override
    public void onTapHealthCareInfo(HealthCareInfoVO healthCareInfo) {

    }
}
