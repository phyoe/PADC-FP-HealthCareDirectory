package com.padc.healthcaredirectory.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.HealthCareAdapter;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicListFragment extends BaseFragment {

    @BindView(R.id.rv_clinics)
    RecyclerView rvClinics;

    private HealthCareAdapter mHealthCareAdapter;
    private HealthCareViewHolder.ControllerHealthCareItem controllerHealthCareItem;

    public static ClinicListFragment newInstance() {
        ClinicListFragment fragment = new ClinicListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HealthCareViewHolder.ControllerHealthCareItem){
            controllerHealthCareItem = (HealthCareViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
        ButterKnife.bind(this, rootView);

        //List<HealthCareVO> healthCareList = HealthCareModel.getInstance().getHealthCareList();
        List<HealthCareVO> healthCareList = super.getTempData(R.string.health_care_clinic, HealthCareDirectoryConstants.FRAGMENT_CLINIC);;

        mHealthCareAdapter = new HealthCareAdapter(healthCareList, controllerHealthCareItem);
        rvClinics.setAdapter(mHealthCareAdapter);

        rvClinics.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));


        return rootView;
    }

}
