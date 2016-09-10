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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.fragments.HealthCarePagerFragment;
import com.padc.healthcaredirectory.utils.MMFontUtils;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HealthCareViewHolder.ControllerHealthCareItem {

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
        //toggle.syncState();

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
                navigateToHealthCareClinicList();
                return true;
            case R.id.menu_health_care_doctors:
                navigateToHealthCareDoctorList();
                return true;
            case R.id.menu_health_care_veterinary:
                navigateToHealthCareVeterinaryList();
                return true;
            case R.id.menu_health_care_diseases:
                navigateToHealthCareDiseaseList();
                return true;
            case R.id.menu_health_care_articles:
                navigateToHealthCareArticleList();
                return true;
            case R.id.menu_favourite_list:
                navigateToHealthCareFavList();
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

    private void navigateToHealthCareClinicList() {

    }

    private void navigateToHealthCareDoctorList() {

    }

    private void navigateToHealthCareVeterinaryList() {

    }

    private void navigateToHealthCareDiseaseList() {

    }

    private void navigateToHealthCareFavList() {

    }

    private void navigateToHelp() {

    }

    private void navigateToHealthCareArticleList() {

    }

    private void navigateToAboutUs() {

    }

    @Override
    public void onTapHealthCare(HealthCareVO healthcare, ImageView ivHealthCare) {

        Toast.makeText(getApplicationContext(), "Detail View will show ...", Toast.LENGTH_SHORT).show();

        Intent intent = HospitalDetailActivity.newIntent(healthcare.getName());
        startActivity(intent);
    }
}
