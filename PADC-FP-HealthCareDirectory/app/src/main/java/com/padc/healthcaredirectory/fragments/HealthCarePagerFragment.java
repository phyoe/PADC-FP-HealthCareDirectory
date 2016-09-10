package com.padc.healthcaredirectory.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.HealthCarePagerAdapter;
import com.padc.healthcaredirectory.utils.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCarePagerFragment extends Fragment {

    @BindView(R.id.tablayout_health_care)
    TabLayout tabLayoutHealthCare;

    @BindView(R.id.pager_health_care)
    ViewPager pagerHealthCare;

    private HealthCarePagerAdapter mHealthCareAdapter;

    public static Fragment newInstance() {
        HealthCarePagerFragment fragment = new HealthCarePagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHealthCareAdapter = new HealthCarePagerAdapter(getActivity().getSupportFragmentManager());
        mHealthCareAdapter.addTab(HospitalListFragment.newInstance(), getString(R.string.health_care_hospital));
        mHealthCareAdapter.addTab(ClinicListFragment.newInstance(), getString(R.string.health_care_clinic));
        mHealthCareAdapter.addTab(PharmacyListFragment.newInstance(), getString(R.string.health_care_pharmacy));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_pager_health_care, container, false);
        ButterKnife.bind(this, rootView);

        pagerHealthCare.setAdapter(mHealthCareAdapter);
        pagerHealthCare.setOffscreenPageLimit(mHealthCareAdapter.getCount());

        tabLayoutHealthCare.setupWithViewPager(pagerHealthCare);

        MMFontUtils.applyMMFontToTabLayout(tabLayoutHealthCare);

        return rootView;
    }

}
