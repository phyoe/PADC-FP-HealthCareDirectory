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
public class PharmacyListFragment extends Fragment {

    public static Fragment newInstance() {
        PharmacyListFragment fragment = new PharmacyListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pharmacy_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
