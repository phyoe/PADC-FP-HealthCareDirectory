package com.padc.healthcaredirectory.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.HealthCareServiceAdapter;
import com.padc.healthcaredirectory.data.models.HealthCareServiceModel;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends BaseFragment {

    @BindView(R.id.rv_search_result)
    RecyclerView rvSearchResult;

    private HealthCareServiceAdapter mHealthCareServiceAdapter;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareServiceItem;

    private static String mSearchText;

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof HealthCareServiceViewHolder.ControllerHealthCareItem){
            mControllerHealthCareServiceItem = (HealthCareServiceViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, rootView);

        mSearchText = getArguments().getString("searchText");

        Log.e("searchText : ", mSearchText);

        List<HealthCareServiceVO> healthCareList = HealthCareServiceModel.getInstance().getHealthCareServiceListBySearchText(mSearchText);

        mHealthCareServiceAdapter = new HealthCareServiceAdapter(healthCareList, mControllerHealthCareServiceItem);
        rvSearchResult.setAdapter(mHealthCareServiceAdapter);

        rvSearchResult.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }

    public void onEventMainThread(DataEvent.HealthCareServiceDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<HealthCareServiceVO> newHealthCareServiceList = event.getHealthCareServiceListBySearchText(mSearchText);
        mHealthCareServiceAdapter.setNewDataBySearchText(newHealthCareServiceList, mSearchText);
        mHealthCareServiceAdapter.notifyDataSetChanged();
    }
    @Override
    public void onStart() {
        super.onStart();
        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

}
