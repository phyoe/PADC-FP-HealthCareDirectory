package com.padc.healthcaredirectory.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.CategoryAdapter;
import com.padc.healthcaredirectory.data.models.CategoryModel;
import com.padc.healthcaredirectory.data.vos.CategoryVO;
import com.padc.healthcaredirectory.views.holders.CategoryViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorCategoryListFragment extends BaseFragment {

    @BindView(R.id.rv_doctor_categories)
    RecyclerView rvDoctorCategories;

    private CategoryAdapter mCategoryAdapter;
    private CategoryViewHolder.ControllerCategoryItem mControllerCategoryItem;

    public static DoctorCategoryListFragment newInstance(){
        DoctorCategoryListFragment fragment = new DoctorCategoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<CategoryVO> categoryList = CategoryModel.getInstance().getCategoryList();
        mCategoryAdapter = new CategoryAdapter(categoryList, mControllerCategoryItem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CategoryViewHolder.ControllerCategoryItem){
            mControllerCategoryItem = (CategoryViewHolder.ControllerCategoryItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_doctor_category_list, container, false);
        ButterKnife.bind(this, rootView);

        List<CategoryVO> categoryList = CategoryModel.getInstance().getCategoryList();
        //List<CategoryVO> categoryList = super.setTempData();

        mCategoryAdapter = new CategoryAdapter(categoryList, mControllerCategoryItem);
        rvDoctorCategories.setAdapter(mCategoryAdapter);

        rvDoctorCategories.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }
}
