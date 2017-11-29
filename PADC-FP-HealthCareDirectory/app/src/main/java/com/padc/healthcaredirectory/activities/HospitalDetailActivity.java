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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HospitalDetailActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_favourite)
    FloatingActionButton fabFavourite;

    @BindView(R.id.img_service)
    ImageView imgService;

    @BindView(R.id.tv_service_name)
    TextView tvServiceName;

    @BindView(R.id.tv_service_category)
    TextView tvServiceCategory;

    @BindView(R.id.tv_price_category)
    TextView tvPriceCategory;

    @BindView(R.id.tv_service_address)
    TextView tvServiceAddress;

    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;

    @BindView(R.id.tv_service_email)
    TextView tvServiceEmail;

    @BindView(R.id.tv_service_website)
    TextView tvServiceWebsite;

    @BindView(R.id.tv_service_facebook)
    TextView tvServiceFacebook;

    /**
    @BindView(R.id.img_service_map)
    ImageView imgServiceMap;
    /**/

    @BindView(R.id.tv_detail_info_title)
    TextView tvDetailInfoTitle;

    @BindView(R.id.tv_service_operations)
    TextView tvServiceOperations;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                String imageUrl = mHealthCareService.getImage();
                Toast.makeText(HealthCareDirectoryApp.getContext(), getString(R.string.str_share_unicode), Toast.LENGTH_SHORT).show();
                super.sendViaShareIntent(mHealthCareService.getCategoryMM() + "\n" + imageUrl);
                return true;
        }

        return super.onOptionsItemSelected(item);
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
                Toast.makeText(HealthCareDirectoryApp.getContext(), "Coming Soon ..." , Toast.LENGTH_SHORT).show();
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
                    mHealthCareService.setPhones(HealthCareServiceVO.loadHealthCareServicePhoneByServiceId(mHealthCareServiceId));
                    mHealthCareService.setFax(HealthCareServiceVO.loadHealthCareServiceFaxByServiceId(mHealthCareServiceId));
                    mHealthCareService.setTags(HealthCareServiceVO.loadHealthCareServiceTagsByServiceId(mHealthCareServiceId));
                    mHealthCareService.setOperations(HealthCareServiceVO.loadHealthCareServiceOperationsByServiceId(mHealthCareServiceId));

                    /**
                    //for Speciality and TimeSlots
                    ArrayList<AvailableDoctorVO> doctorList = HealthCareServiceVO.loadHealthCareServiceDoctorsByServiceId(mHealthCareServiceId);
                    for(int i=0 ; i < doctorList.size() ; i++){
                        AvailableDoctorVO doctor = doctorList.get(i);
                        long doctor_id = doctor.getDoctorId();

                        SpecialityVO speciality = HealthCareServiceVO.loadHealthCareServiceDoctorSpecialityByServiceId(mHealthCareServiceId, doctor_id);
                        doctor.setSpeciality(speciality);

                        ArrayList<TimeSlotVO> timeSlots = HealthCareServiceVO.loadHealthCareServiceDoctorTimeslotsByServiceId(mHealthCareServiceId, doctor_id);
                        doctor.setTimeSlots(timeSlots);

                        doctorList.add(doctor);
                    }
                    mHealthCareService.setDoctors(doctorList);
                    /**/

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
        tvServiceName.setText(healthCareService.getHealthCareName());

        String category_mm = healthCareService.getCategoryMM();
        String tags = "";
        for(int i=0 ; i < healthCareService.getTags().size() ; i++) {
            tags = tags + "(" + healthCareService.getTags().get(i).getTagNameMM() + ") ";
        }
        tvServiceCategory.setText(category_mm + "" + tags);

        int price_category = healthCareService.getPriceCategory();
        String star = "";
        for(int s=1 ; s<=price_category ; s++)
            star = star + "★";
        tvPriceCategory.setText(star);

        tvDetailInfoTitle.setText(category_mm + "" + "အခ်က္အလက္");

        String address = (!healthCareService.getAddress().isEmpty())? healthCareService.getAddress() : HealthCareDirectoryConstants.STR_NO_DATA;
        tvServiceAddress.setText(address);

        String phones = "";
        for(int i=0 ; i < healthCareService.getPhones().size() ; i++){
            if(i == healthCareService.getPhones().size()-1)
                phones = phones + healthCareService.getPhones().get(i).getPhoneName();
            else
                phones = phones + healthCareService.getPhones().get(i).getPhoneName() + "\n";
        }
        //tvServicePhone.setText(phones);
        String phone = mHealthCareService.getPhones().get(0).getPhoneName();
        tvServicePhone.setText((phone != null && !phone.isEmpty())? phone : HealthCareDirectoryConstants.STR_NO_DATA);

        String email = (!healthCareService.getEmail().isEmpty())? healthCareService.getEmail() : HealthCareDirectoryConstants.STR_NO_DATA;
        tvServiceEmail.setText(email);

        String website = (!healthCareService.getWebsite().isEmpty())? healthCareService.getWebsite() : HealthCareDirectoryConstants.STR_NO_DATA;
        tvServiceWebsite.setText(website);

        String facebook = (!healthCareService.getFacebook().isEmpty())? healthCareService.getFacebook() : HealthCareDirectoryConstants.STR_NO_DATA;
        tvServiceFacebook.setText(facebook);

        collapsingToolbar.setTitle(healthCareService.getHealthCareName());

        String operations = "";
        for(int i=0 ; i < healthCareService.getOperations().size() ; i++) {
            if(i == healthCareService.getOperations().size()-1)
                operations = operations + healthCareService.getOperations().get(i).getOperationName();
            else
                operations = operations + healthCareService.getOperations().get(i).getOperationName() + "\n";
        }
        if(operations != null && !operations.isEmpty()){
            tvServiceOperations.setText(operations);
        } else {
            tvServiceOperations.setText(getString(R.string.default_opeartion_hospital));
        }

        String imageUrl = healthCareService.getImage();
        Glide.with(imgService.getContext())
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.healthcare_photo_placeholder)
                .error(R.drawable.healthcare_photo_placeholder)
                .into(imgService);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = HealthCareDirectoryApp.getContext();
            String transitionName = context.getResources().getString(R.string.healthcare_list_detail_transition_name);
            imgService.setTransitionName(transitionName);
        }
    }

    @OnClick(R.id.tv_service_website)
    public void onTapWebsiteURL(View view) {
        String website = mHealthCareService.getWebsite();
        if(website != null && !website.isEmpty()) {
            super.openInWebsiteUrl(website);
        } else {
            Toast.makeText(HealthCareDirectoryApp.getContext(), HealthCareDirectoryConstants.STR_NO_DATA_UNICODE, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_service_facebook)
    public void onTapFacebook(View view) {
        String facebook = mHealthCareService.getFacebook();
        if(facebook != null && !facebook.isEmpty()) {
            super.openInFacebook(facebook);
        } else {
            Toast.makeText(HealthCareDirectoryApp.getContext(), HealthCareDirectoryConstants.STR_NO_DATA_UNICODE, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_service_phone)
    public void onTapPhone(View view) {
        String phone = mHealthCareService.getPhones().get(0).getPhoneName();
        if(phone != null && !phone.isEmpty()) {
            super.makeCall(phone);
        } else {
            Toast.makeText(HealthCareDirectoryApp.getContext(), HealthCareDirectoryConstants.STR_NO_DATA_UNICODE, Toast.LENGTH_SHORT).show();
        }
    }
}
