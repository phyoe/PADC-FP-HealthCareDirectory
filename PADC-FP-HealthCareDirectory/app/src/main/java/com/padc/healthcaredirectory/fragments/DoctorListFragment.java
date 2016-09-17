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
import com.padc.healthcaredirectory.adapters.DoctorAdapter;
import com.padc.healthcaredirectory.data.models.DoctorModel;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.views.holders.DoctorViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorListFragment extends BaseFragment {

    @BindView(R.id.rv_doctors)
    RecyclerView rvDoctors;

    private DoctorAdapter mDoctorAdapter;
    private DoctorViewHolder.ControllerDocotorItem mControllerDocotorItem;

    public static DoctorListFragment newInstance(){
        DoctorListFragment fragment = new DoctorListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DoctorViewHolder.ControllerDocotorItem){
            mControllerDocotorItem = (DoctorViewHolder.ControllerDocotorItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        ButterKnife.bind(this, rootView);

        List<DoctorVO> doctorList = DoctorModel.getInstance().getDoctorList();
        //List<HealthCareVO> healthCareList = super.setTempData(R.string.health_care_hospital, HealthCareDirectoryConstants.FRAGMENT_HOSPITAL);

        mDoctorAdapter = new DoctorAdapter(doctorList, mControllerDocotorItem);
        rvDoctors.setAdapter(mDoctorAdapter);

        rvDoctors.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;

    }

}
