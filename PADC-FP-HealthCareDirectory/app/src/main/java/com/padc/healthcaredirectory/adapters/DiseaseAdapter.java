package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.DiseaseVO;
import com.padc.healthcaredirectory.views.holders.DiseaseViewHolder;

import java.util.List;

/** extends RecyclerView.Adapter<HealthCareInfoViewHolder>
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseViewHolder>{

    private LayoutInflater mInflater;
    private List<DiseaseVO> mDiseaseList;
    private DiseaseViewHolder.ControllerDiseaseItem mControllerDiseaseItem;

    public DiseaseAdapter(List<DiseaseVO> mDiseaseList, DiseaseViewHolder.ControllerDiseaseItem mControllerDiseaseItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        this.mDiseaseList = mDiseaseList;
        this.mControllerDiseaseItem = mControllerDiseaseItem;
    }

    @Override
    public DiseaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_disease, parent, false);
        return new DiseaseViewHolder(itemView,mControllerDiseaseItem);

    }

    @Override
    public void onBindViewHolder(DiseaseViewHolder holder, int position) {
        holder.bindData(mDiseaseList.get(position));

    }

    @Override
    public int getItemCount() {
        return mDiseaseList.size();
    }

    public void setNewData(List<DiseaseVO> newDiseaseList) {
        mDiseaseList = newDiseaseList;
        notifyDataSetChanged();//framework method
    }
}
