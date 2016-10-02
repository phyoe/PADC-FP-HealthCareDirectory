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
import com.padc.healthcaredirectory.adapters.HealthCareAdapter;
import com.padc.healthcaredirectory.adapters.HealthCareServiceAdapter;
import com.padc.healthcaredirectory.adapters.VeterinaryClinicAdapter;
import com.padc.healthcaredirectory.data.models.HealthCareInfoModel;
import com.padc.healthcaredirectory.data.models.HealthCareServiceModel;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;
import com.padc.healthcaredirectory.views.holders.HealthCareViewHolder;
import com.padc.healthcaredirectory.views.holders.VeterinaryClinicViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
        */
public class VeterinaryClinicListFragment extends BaseFragment  implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.rv_veterinary_clinic)
    RecyclerView rvVeterinaryClinic;



    private HealthCareServiceAdapter mHealthCareServiceAdapter;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareServiceItem;

    public static VeterinaryClinicListFragment newInstance() {
        VeterinaryClinicListFragment fragment = new VeterinaryClinicListFragment();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_veterinary_clinic_list, container, false);
        ButterKnife.bind(this, rootView);

        /**
         List<HealthCareVO> healthCareList = HealthCareModel.getInstance().getHealthCareList();
         //List<HealthCareVO> healthCareList = super.setTempData(R.string.health_care_hospital, HealthCareDirectoryConstants.FRAGMENT_HOSPITAL);

         mHealthCareAdapter = new HealthCareAdapter(healthCareList, mControllerHealthCareItem);
         rvHospitals.setAdapter(mHealthCareAdapter);
         /**/

        /**/
        List<HealthCareServiceVO> healthCareList = HealthCareServiceModel.getInstance().getHealthCareServiceList();

        mHealthCareServiceAdapter = new HealthCareServiceAdapter(healthCareList, mControllerHealthCareServiceItem);
        rvVeterinaryClinic.setAdapter(mHealthCareServiceAdapter);
        /**/

        rvVeterinaryClinic.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         if(context instanceof HealthCareViewHolder.ControllerHealthCareItem){
         mControllerHealthCareItem = (HealthCareViewHolder.ControllerHealthCareItem) context;
         } else {
         throw new RuntimeException("Unsupported Type");
         }
         /**/
        /**/
        if(context instanceof HealthCareServiceViewHolder.ControllerHealthCareItem){
            mControllerHealthCareServiceItem = (HealthCareServiceViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
        /**/
    }

    public void onEventMainThread(DataEvent.HealthCareServiceDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<HealthCareServiceVO> newHealthCareServiceList = event.getHealthCareServiceList();
        mHealthCareServiceAdapter.setNewData(newHealthCareServiceList);
        mHealthCareServiceAdapter.notifyDataSetChanged();
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

            List<HealthCareServiceVO> newHealthCareServiceList = HealthCareServiceModel.getInstance().getHealthCareServiceList();
            mHealthCareServiceAdapter.setNewData(newHealthCareServiceList);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(HealthCareDirectoryConstants.HEALTHCARE_SERVICE_LIST_LOADER, null, this);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                HealthCareContract.HealthCareServiceEntry.CONTENT_URI,
                null,
                null,
                null,
                HealthCareContract.HealthCareServiceEntry.COLUMN_HEALTHCARE_SERVICE_NAME + " DESC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<HealthCareServiceVO> healthCareServiceList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                HealthCareServiceVO healthCareService = HealthCareServiceVO.parseFromCursor(data);
                //HealthCareServiceVO.setPhones(HealthCareInfoVO.loadHealthCareInfoAuthorByInfoId(healthCareService.getId()));
                healthCareServiceList.add(healthCareService);
            } while (data.moveToNext());
        }

        Log.d(HealthCareDirectoryApp.TAG, "Retrieved healthCareService DESC : " + healthCareServiceList.size());
        mHealthCareServiceAdapter.setNewData(healthCareServiceList);

        HealthCareServiceModel.getInstance().setStoredData(healthCareServiceList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
