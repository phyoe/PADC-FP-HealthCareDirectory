package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.fragments.DoctorListFragment;
import com.padc.healthcaredirectory.views.holders.DoctorViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorListActivity extends AppCompatActivity
implements DoctorViewHolder.ControllerDocotorItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
    private DoctorVO mDoctor;
    private String mCategoryName;
    private int mDoctorId;

    private static final String IE_CATEGORY_NAME = "IE_CATEGORY_NAME";
    private static final String IE_DOCTOR_ID = "IE_DOCTOR_ID";
    /**/

    public static Intent newIntent(String categoryName, int doctorId ){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), DoctorListActivity.class);
        //intent.putExtra(IE_CATEGORY_NAME, categoryName);
        //intent.putExtra(IE_DOCTOR_ID, doctorId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

       // mCategoryName = getIntent().getStringExtra(IE_CATEGORY_NAME);
       // mDoctorId = getIntent().getIntExtra(IE_DOCTOR_ID, 0);
       // mDoctor = DoctorModel.getInstance().getDoctorById(mDoctorId);

        if (savedInstanceState == null) {
            DoctorListFragment fragment = DoctorListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }

    }

    @Override
    public void onTapDoctor(DoctorVO doctor) {

        int id = doctor.getId();

        Intent intent = DoctorDetailActivity.newIntent(id);
        startActivity(intent);
    }
}
