package com.padc.healthcaredirectory.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.DiseaseAdapter;
import com.padc.healthcaredirectory.adapters.HealthCareInfoAdapter;
import com.padc.healthcaredirectory.data.models.HealthCareInfoModel;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.DiseaseVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class DiseaseListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.rv_disease)
    RecyclerView rvDisease;

    private HealthCareInfoAdapter mHealthCareInfoAdapter;
    private HealthCareInfoViewHolder.ControllerHealthCareInfoItem mControllerHealthCareInfoItem;
    private List<HealthCareInfoVO> diseaseList;
    public static final String TYPE_DISEASE = "disease-info";


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
        List<HealthCareInfoVO> healthCareInfoList = HealthCareInfoModel.getInstance().getHealthCareInfoList();
/*
        diseaseList=new ArrayList<>();

        for(HealthCareInfoVO healthcareInfo:healthCareInfoList)
        {
            if(healthcareInfo.getInfoType()==TYPE_DISEASE)
            {
                diseaseList.add(healthcareInfo);
                Log.d(HealthCareDirectoryApp.TAG,healthcareInfo.getInfoType() );
            }
        }

*/

        mHealthCareInfoAdapter = new HealthCareInfoAdapter(healthCareInfoList, mControllerHealthCareInfoItem);
        rvDisease.setAdapter(mHealthCareInfoAdapter);

        rvDisease.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));
        return rootView;

    }

    public void onEventMainThread(DataEvent.HealthCareInfoDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<HealthCareInfoVO> newHealthCareInfoList = event.getHealthCareInfoList();
        mHealthCareInfoAdapter.setNewData(newHealthCareInfoList, TYPE_DISEASE);
        mHealthCareInfoAdapter.notifyDataSetChanged();
    }


/*
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

    */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HealthCareInfoViewHolder.ControllerHealthCareInfoItem){
            mControllerHealthCareInfoItem = (HealthCareInfoViewHolder.ControllerHealthCareInfoItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //For Persistence Layer
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoadedBroadcastReceiver, new IntentFilter(HealthCareInfoModel.BROADCAST_DATA_LOADED));

        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        super.onStop();
        //For Persistence Layer
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDataLoadedBroadcastReceiver);

        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    private BroadcastReceiver mDataLoadedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO instructions when the new data is ready.
            String extra = intent.getStringExtra("key-for-extra");
            Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

            List<HealthCareInfoVO> newHealthCareInfoList = HealthCareInfoModel.getInstance().getHealthCareInfoList();
            mHealthCareInfoAdapter.setNewData(newHealthCareInfoList,TYPE_DISEASE);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(HealthCareDirectoryConstants.HEALTHCARE_INFO_LIST_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                HealthCareContract.HealthCareInfoEntry.CONTENT_URI,
                null,
                null,
                null,
                HealthCareContract.HealthCareInfoEntry.COLUMN_TITLE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        List<HealthCareInfoVO> healthCareInfoList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                HealthCareInfoVO healthCareInfo = HealthCareInfoVO.parseFromCursor(data);
                healthCareInfo.setAuthor(HealthCareInfoVO.loadHealthCareInfoAuthorByInfoId(healthCareInfo.getId()));
                healthCareInfoList.add(healthCareInfo);
            } while (data.moveToNext());
        }

        Log.d(HealthCareDirectoryApp.TAG, "Retrieved healthCareInfo DESC : " + healthCareInfoList.size());
        mHealthCareInfoAdapter.setNewData(healthCareInfoList,TYPE_DISEASE);

        HealthCareInfoModel.getInstance().setStoredData(healthCareInfoList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
