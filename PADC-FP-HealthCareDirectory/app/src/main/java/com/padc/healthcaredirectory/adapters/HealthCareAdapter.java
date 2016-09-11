package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareVO;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/8/2016.
 */
public class HealthCareAdapter extends RecyclerView.Adapter<HealthCareViewHolder> {

    private LayoutInflater mInflater;
    private List<HealthCareVO> mHealthCareList;
    private HealthCareViewHolder.ControllerHealthCareItem mControllerHealthCareItem;

    public HealthCareAdapter(List<HealthCareVO> healthCareList) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mHealthCareList = healthCareList;
    }

    public HealthCareAdapter(List<HealthCareVO> healthCareList, HealthCareViewHolder.ControllerHealthCareItem controllerHealthCareItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mHealthCareList = healthCareList;
        mControllerHealthCareItem = controllerHealthCareItem;
    }

    @Override
    public HealthCareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_healthcare, parent, false);
        return new HealthCareViewHolder(itemView, mControllerHealthCareItem);
    }

    @Override
    public void onBindViewHolder(HealthCareViewHolder holder, int position) {
       holder.bindData(mHealthCareList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHealthCareList.size();
    }
}
