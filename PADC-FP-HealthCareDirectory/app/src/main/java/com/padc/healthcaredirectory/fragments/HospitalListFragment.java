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
import com.padc.healthcaredirectory.adapters.HealthCareServiceAdapter;
import com.padc.healthcaredirectory.data.models.HealthCareModel;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HospitalListFragment extends BaseFragment {

    @BindView(R.id.rv_hospitals)
    RecyclerView rvHospitals;

    private HealthCareAdapter mHealthCareAdapter;
    private HealthCareViewHolder.ControllerHealthCareItem mControllerHealthCareItem;

    private HealthCareServiceAdapter mHealthCareServiceAdapter;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareServiceItem;

    public static HospitalListFragment newInstance() {
        HospitalListFragment fragment = new HospitalListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /**
        if(context instanceof HealthCareViewHolder.ControllerHealthCareItem){
            mControllerHealthCareItem = (HealthCareViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
        /**/
        if(context instanceof HealthCareServiceViewHolder.ControllerHealthCareItem){
            mControllerHealthCareServiceItem = (HealthCareServiceViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hospital_list, container, false);
        ButterKnife.bind(this, rootView);

        /**/
        List<HealthCareVO> healthCareList = HealthCareModel.getInstance().getHealthCareList();
        //List<HealthCareVO> healthCareList = super.setTempData(R.string.health_care_hospital, HealthCareDirectoryConstants.FRAGMENT_HOSPITAL);

        mHealthCareAdapter = new HealthCareAdapter(healthCareList, mControllerHealthCareItem);
        rvHospitals.setAdapter(mHealthCareAdapter);
        /**/

        /**
        List<HealthCareServiceVO> healthCareList = HealthCareServiceModel.getInstance().getHealthCareServiceList();

        mHealthCareServiceAdapter = new HealthCareServiceAdapter(healthCareList, mControllerHealthCareServiceItem);
        rvHospitals.setAdapter(mHealthCareServiceAdapter);
        /**/

        rvHospitals.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }
}
