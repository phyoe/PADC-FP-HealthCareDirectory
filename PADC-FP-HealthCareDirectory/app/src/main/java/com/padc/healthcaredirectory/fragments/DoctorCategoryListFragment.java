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
import com.padc.healthcaredirectory.adapters.DoctorCategoryAdapter;
import com.padc.healthcaredirectory.data.models.DoctorModel;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.views.holders.DoctorCategoryViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorCategoryListFragment extends BaseFragment {

    @BindView(R.id.rv_doctor_categories)
    RecyclerView rvDoctorCategories;

    private DoctorCategoryAdapter mDoctorCategoryAdapter;
    private DoctorCategoryViewHolder.ControllerDocotorCategoryItem mControllerDocotorCategoryItem;

    public static DoctorCategoryListFragment newInstance(){
        DoctorCategoryListFragment fragment = new DoctorCategoryListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DoctorCategoryViewHolder.ControllerDocotorCategoryItem){
            mControllerDocotorCategoryItem = (DoctorCategoryViewHolder.ControllerDocotorCategoryItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_doctor_category_list, container, false);
        ButterKnife.bind(this, rootView);

        List<DoctorVO> doctorList = DoctorModel.getInstance().getDoctorList();
        //List<HealthCareVO> healthCareList = super.setTempData(R.string.health_care_hospital, HealthCareDirectoryConstants.FRAGMENT_HOSPITAL);

        mDoctorCategoryAdapter = new DoctorCategoryAdapter(doctorList, mControllerDocotorCategoryItem);
        rvDoctorCategories.setAdapter(mDoctorCategoryAdapter);


        rvDoctorCategories.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }

}
