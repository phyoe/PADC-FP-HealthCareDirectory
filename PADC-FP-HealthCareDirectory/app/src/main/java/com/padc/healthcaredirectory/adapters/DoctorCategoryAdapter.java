package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DoctorCategoryVO;
import com.padc.healthcaredirectory.data.vos.DoctorVO;
import com.padc.healthcaredirectory.views.holders.DoctorCategoryViewHolder;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/16/2016.
 */
public class DoctorCategoryAdapter extends RecyclerView.Adapter<DoctorCategoryViewHolder> {

    private LayoutInflater mInflater;

    private List<DoctorVO> mDoctorList;

    private DoctorCategoryViewHolder.ControllerDocotorCategoryItem mControllerDocotorCategoryItem;

    public DoctorCategoryAdapter(List<DoctorVO> doctorList,
                                 DoctorCategoryViewHolder.ControllerDocotorCategoryItem controllerDocotorCategoryItem) {

        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mDoctorList = doctorList;
        mControllerDocotorCategoryItem = controllerDocotorCategoryItem;
    }

    @Override
    public DoctorCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_doctor_category, parent, false);
        return new DoctorCategoryViewHolder(itemView, mControllerDocotorCategoryItem);
    }

    @Override
    public void onBindViewHolder(DoctorCategoryViewHolder holder, int position) {
        holder.bindData(mDoctorList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }
}
