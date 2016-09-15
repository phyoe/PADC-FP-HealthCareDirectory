package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.CategoryVO;
import com.padc.healthcaredirectory.views.holders.CategoryViewHolder;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/13/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private LayoutInflater mInflater;
    private List<CategoryVO> mCategoryList;
    private CategoryViewHolder.ControllerCategoryItem mControllerCategoryItem;

    public CategoryAdapter(List<CategoryVO> categoryList) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mCategoryList = categoryList;
    }

    public CategoryAdapter(List<CategoryVO> categoryList, CategoryViewHolder.ControllerCategoryItem controllerCategoryItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mCategoryList = categoryList;
        mControllerCategoryItem = controllerCategoryItem;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_doctor, parent, false);
        return new CategoryViewHolder(itemView, mControllerCategoryItem);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindData(mCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
