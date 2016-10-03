package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/21/2016.
 */
public class HealthCareServiceAdapter  extends RecyclerView.Adapter<HealthCareServiceViewHolder> {

    private LayoutInflater mInflater;
    private List<HealthCareServiceVO> mHealthCareServiceList;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareItem;

    public HealthCareServiceAdapter(List<HealthCareServiceVO> healthCareList, HealthCareServiceViewHolder.ControllerHealthCareItem controllerHealthCareItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mHealthCareServiceList = healthCareList;
        mControllerHealthCareItem = controllerHealthCareItem;
    }

    @Override
    public HealthCareServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_healthcare, parent, false);
        return new HealthCareServiceViewHolder(itemView, mControllerHealthCareItem);
    }

    @Override
    public void onBindViewHolder(HealthCareServiceViewHolder holder, int position) {
        holder.bindData(mHealthCareServiceList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHealthCareServiceList.size();
    }

    public void setNewData(List<HealthCareServiceVO> newHealthCareServiceList) {
        mHealthCareServiceList = newHealthCareServiceList;
        notifyDataSetChanged();//framework method
    }

    public void setNewData(List<HealthCareServiceVO> newHealthCareServiceList, String category) {
        //Filter here
        List<HealthCareServiceVO> tempList = new ArrayList<>();
        for(HealthCareServiceVO healthcareService: newHealthCareServiceList){

            if(healthcareService.getCategory() == null || healthcareService.getCategory().isEmpty()){
                healthcareService.setCategory(HealthCareDirectoryConstants.STR_HOSPITAL);
            } else if(healthcareService.getCategory().equals(category)){
                tempList.add(healthcareService);
                Log.d(HealthCareDirectoryApp.TAG,healthcareService.getCategory());
            }
        }
        mHealthCareServiceList = tempList;
        notifyDataSetChanged();//framework method
    }
}