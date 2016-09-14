package com.padc.healthcaredirectory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorCategoryListFragment extends Fragment {

    public static DoctorCategoryListFragment newInstance(){
        return new DoctorCategoryListFragment();
    }

    public DoctorCategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_category_list, container, false);
    }

}
