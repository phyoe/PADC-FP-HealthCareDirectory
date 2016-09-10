package com.padc.healthcaredirectory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicListFragment extends Fragment {

    public static Fragment newInstance() {
        ClinicListFragment fragment = new ClinicListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
