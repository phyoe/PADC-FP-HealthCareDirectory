package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.views.holders.VeterinaryClinicViewHolder;

import java.util.List;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class VeterinaryClinicAdapter extends RecyclerView.Adapter<VeterinaryClinicViewHolder> {

    private String mCurrentClick;
    private LayoutInflater mInflater;
    private List<VeterinaryClinicVO> mVeterinaryClinicList;
    private VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem mControllerVeterinaryClinicItem;

    public VeterinaryClinicAdapter(List<VeterinaryClinicVO> mVeterinaryClinicList, VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem controllerVeterinaryClinicItem, String currentClick) {
        this.mCurrentClick=currentClick;
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        this.mVeterinaryClinicList = mVeterinaryClinicList;
        this.mControllerVeterinaryClinicItem = controllerVeterinaryClinicItem;
    }

    @Override
    public VeterinaryClinicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_veterinary_clinic_list, parent, false);
        return new VeterinaryClinicViewHolder(itemView,mControllerVeterinaryClinicItem,mCurrentClick);
    }

    @Override
    public void onBindViewHolder(VeterinaryClinicViewHolder holder, int position) {
        holder.bindData(mVeterinaryClinicList.get(position));
    }

    @Override
    public int getItemCount() {
        return mVeterinaryClinicList.size();
    }

    public void setNewData(List<VeterinaryClinicVO> newVeterinaryClinicList) {
        mVeterinaryClinicList = newVeterinaryClinicList;
        notifyDataSetChanged();//framework method
    }
}
