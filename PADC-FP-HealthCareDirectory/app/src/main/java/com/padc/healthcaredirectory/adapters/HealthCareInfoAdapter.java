package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareInfoAdapter  extends RecyclerView.Adapter<HealthCareInfoViewHolder> {

    private LayoutInflater mInflater;
    private List<HealthCareInfoVO> mHealthCareInfoList;
    private HealthCareInfoViewHolder.ControllerHealthCareInfoItem mControllerHealthCareInfoItem;

    public HealthCareInfoAdapter(List<HealthCareInfoVO> healthCareInfoList, HealthCareInfoViewHolder.ControllerHealthCareInfoItem controllerHealthCareInfoItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mHealthCareInfoList = healthCareInfoList;
        mControllerHealthCareInfoItem = controllerHealthCareInfoItem;
    }

    @Override
    public HealthCareInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_article, parent, false);
        return new HealthCareInfoViewHolder(itemView, mControllerHealthCareInfoItem);
    }

    @Override
    public void onBindViewHolder(HealthCareInfoViewHolder holder, int position) {
        holder.bindData(mHealthCareInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHealthCareInfoList.size();
    }

    public void setNewData(List<HealthCareInfoVO> newHealthCareInfoList) {
        mHealthCareInfoList = newHealthCareInfoList;
        notifyDataSetChanged();//framework method
    }

    public void setNewData(List<HealthCareInfoVO> newHelathCareInfoList, String infoType) {
        //Filter here
        List<HealthCareInfoVO> tempList = new ArrayList<>();
        for(HealthCareInfoVO healthcareInfo: newHelathCareInfoList){
            if(healthcareInfo.getInfoType() == null || healthcareInfo.getInfoType().isEmpty()){
                healthcareInfo.setInfoType(HealthCareDirectoryConstants.STR_ARTICLE);
            }
            if(healthcareInfo.getInfoType().contains(infoType)){
                tempList.add(healthcareInfo);
                Log.d(HealthCareDirectoryApp.TAG,healthcareInfo.getInfoType() );
            }
        }
        mHealthCareInfoList = tempList;
        notifyDataSetChanged();


    }
}