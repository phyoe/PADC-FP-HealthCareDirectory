package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DiseaseVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.views.holders.DiseaseViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareInfoViewHolder;

import java.util.List;

/** extends RecyclerView.Adapter<HealthCareInfoViewHolder>
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseViewHolder> {


    private LayoutInflater mInflater;
    private List<HealthCareInfoVO> mHealthCareInfoList;
    private DiseaseViewHolder.ControllerHealthCareInfoItem mControllerHealthCareInfoItem;

    public DiseaseAdapter(List<HealthCareInfoVO> healthCareInfoList, DiseaseViewHolder.ControllerHealthCareInfoItem controllerHealthCareInfoItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mHealthCareInfoList = healthCareInfoList;
        mControllerHealthCareInfoItem = controllerHealthCareInfoItem;
    }

    @Override
    public DiseaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DiseaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
