package com.padc.healthcaredirectory.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.DiseaseAdapter;
import com.padc.healthcaredirectory.data.vos.DiseaseVO;
import com.padc.healthcaredirectory.views.holders.DiseaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class DiseaseListFragment extends BaseFragment {

    @BindView(R.id.rv_disease)
    RecyclerView rvDisease;

    private DiseaseAdapter mDiseaseAdapter;
    private DiseaseViewHolder.ControllerDiseaseItem controllerDiseaseItem;

    public static DiseaseListFragment newInstance() {
        DiseaseListFragment fragment = new DiseaseListFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_disease_list, container, false);
        ButterKnife.bind(this, rootView);

        //List<HealthCareVO> healthCareList = HealthCareModel.getInstance().getHealthCareList();
        List<DiseaseVO> diseaseList = setTempData();

        mDiseaseAdapter = new DiseaseAdapter(diseaseList,controllerDiseaseItem);
        rvDisease.setAdapter(mDiseaseAdapter);

        rvDisease.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));
        return rootView;

    }



    protected List<DiseaseVO> setTempData(){
        List<DiseaseVO> diseaseList = new ArrayList<>();


        for(int i=0 ; i<10 ; i++){
            DiseaseVO disease = new DiseaseVO();
            disease.setId(001);
            disease.setName("နား၊ ႏွာေခါင္း၊ လည္ေခ်ာင္းေရာဂါ");
            disease.setAuthor("သိုက္ရာဇာ");
            disease.setDescription(getString(R.string.dummy_disease_description));
            disease.setReferences("ျမ၀တီ");
            disease.setWebsite("http://www.ygnnews.com/2014/01/blog-post_9225.html");
            disease.setImage("dummy_disease_picture");
            diseaseList.add(disease);
        }
        return diseaseList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DiseaseViewHolder.ControllerDiseaseItem){
            controllerDiseaseItem = (DiseaseViewHolder.ControllerDiseaseItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }
}
