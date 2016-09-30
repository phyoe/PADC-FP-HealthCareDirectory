package com.padc.healthcaredirectory.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HospitalDetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_favourite)
    FloatingActionButton fabFavourite;

    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;

    @BindView(R.id.img_hospital)
    ImageView imgHospital;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    private HealthCareVO mHealthCare;
    private long mHealthCareServiceId;

    private HealthCareServiceVO mHealthCareService;

    private static final String IE_SERVCE_ID = "IE_SERVCE_ID";

    public static Intent newIntent(long serviceId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), HospitalDetailActivity.class);
        intent.putExtra(IE_SERVCE_ID, serviceId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mHealthCareServiceId = getIntent().getLongExtra(IE_SERVCE_ID, 0);

        //Data retrieve from Offline Json
        /**
        mHealthCare = HealthCareModel.getInstance().getHealthCareById(mHospitalId);
        tvHospitalName.setText(mHealthCare.getName());
        collapsingToolbar.setTitle(mHealthCare.getName());

        Glide.with(imgHospital.getContext())
                .load(R.drawable.asia_royal_hospital)
                .fitCenter()
                .placeholder(R.drawable.asia_royal_hospital)
                .error(R.drawable.asia_royal_hospital)
                .into(imgHospital);

         /**/

        //For Persistence Layer
        getSupportLoaderManager().initLoader(HealthCareDirectoryConstants.HEALTHCARE_SERVICE_DETAIL_LOADER, null, this);
    }

    /**
     * For Persistence Layer
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                HealthCareContract.HealthCareServiceEntry.buildHealthCareServiceUriWithServiceId(mHealthCareServiceId),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        boolean findData = false;
        if (data != null && data.moveToFirst()) {
            do {
                if (!findData) {
                    mHealthCareService = HealthCareServiceVO.parseFromCursor(data);
                    //mHealthCareService.setPhones(HealthCareServiceVO.loadHealthCareServicePhoneByServiceId(mHealthCareServiceId));

                    if (mHealthCareService.getHealthCareId() == mHealthCareServiceId) {
                        bindData(mHealthCareService);
                        findData = true;
                    }
                }
            } while (data.moveToNext());
        }
        /**/
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void bindData(HealthCareServiceVO healthCareService) {

        //Data retrieve from Persistence Layer
        tvHospitalName.setText(healthCareService.getHealthCareName());
        collapsingToolbar.setTitle(healthCareService.getHealthCareName());

        String imageUrl = mHealthCareService.getImage();
        Glide.with(imgHospital.getContext())
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.hospital_picture)
                .error(R.drawable.hospital_picture)
                .into(imgHospital);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = HealthCareDirectoryApp.getContext();
            String transitionName = context.getResources().getString(R.string.healthcare_list_detail_transition_name);
            imgHospital.setTransitionName(transitionName);
        }

    }
}
