package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.models.DoctorModel;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.fragments.DoctorListFragment;
import com.padc.healthcaredirectory.views.holders.DoctorViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorListActivity extends AppCompatActivity
implements DoctorViewHolder.ControllerDocotorItem {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DoctorVO mDoctor;
    private String mCategoryName;

    private static final String IE_CATEGORY_NAME = "IE_CATEGORY_NAME";

    public static Intent newIntent(String categoryName){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), DoctorListActivity.class);
        intent.putExtra(IE_CATEGORY_NAME, categoryName);
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

        mCategoryName = getIntent().getStringExtra(IE_CATEGORY_NAME);
        mDoctor = DoctorModel.getInstance().getDoctorByCategoryName(mCategoryName);

        if (savedInstanceState == null) {
            DoctorListFragment fragment = DoctorListFragment.newInstance(mDoctor);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }

    }

    @Override
    public void onTapDoctor(DoctorVO doctor) {

    }
}
