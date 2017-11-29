package com.padc.healthcaredirectory.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.padc.healthcaredirectory.adapters.HealthCareServiceAdapter;
import com.padc.healthcaredirectory.data.models.HealthCareServiceModel;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.HealthCareServiceVO;
import com.padc.healthcaredirectory.events.DataEvent;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class PharmacyListFragment extends BaseFragment
        implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.rv_pharmacies)
    RecyclerView rvPharmacies;

    private HealthCareServiceAdapter mHealthCareServiceAdapter;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareServiceItem;

    public static PharmacyListFragment newInstance() {
        PharmacyListFragment fragment = new PharmacyListFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_pharmacy_list, container, false);
        ButterKnife.bind(this, rootView);

        List<HealthCareServiceVO> healthCareList = HealthCareServiceModel.getInstance().getHealthCareServiceList(HealthCareDirectoryConstants.STR_PHARMACY);

        mHealthCareServiceAdapter = new HealthCareServiceAdapter(healthCareList, mControllerHealthCareServiceItem);
        rvPharmacies.setAdapter(mHealthCareServiceAdapter);
        /**/

        rvPharmacies.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));

        return rootView;
    }
    public void onEventMainThread(DataEvent.HealthCareServiceDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<HealthCareServiceVO> newHealthCareServiceList = event.getHealthCareServiceList(HealthCareDirectoryConstants.STR_PHARMACY);
        mHealthCareServiceAdapter.setNewData(newHealthCareServiceList, HealthCareDirectoryConstants.STR_PHARMACY);
        mHealthCareServiceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        //For Persistence Layer
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoadedBroadcastReceiver, new IntentFilter(HealthCareServiceModel.BROADCAST_DATA_LOADED));

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

    /**
     * For Persistence Layer
     */
    private BroadcastReceiver mDataLoadedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO instructions when the new data is ready.
            String extra = intent.getStringExtra("key-for-extra");
            Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

            List<HealthCareServiceVO> newHealthCareServiceList = HealthCareServiceModel.getInstance().getHealthCareServiceList(HealthCareDirectoryConstants.STR_PHARMACY);
            mHealthCareServiceAdapter.setNewData(newHealthCareServiceList, HealthCareDirectoryConstants.STR_PHARMACY);
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
                healthCareService.setPhones(HealthCareServiceVO.loadHealthCareServicePhoneByServiceId(healthCareService.getHealthCareId()));
                healthCareService.setTags(HealthCareServiceVO.loadHealthCareServiceTagsByServiceId(healthCareService.getHealthCareId()));
                healthCareServiceList.add(healthCareService);
            } while (data.moveToNext());
        }

        Log.d(HealthCareDirectoryApp.TAG, "Retrieved healthCareService DESC : " + healthCareServiceList.size());
        mHealthCareServiceAdapter.setNewData(healthCareServiceList, HealthCareDirectoryConstants.STR_PHARMACY);

        HealthCareServiceModel.getInstance().setStoredData(healthCareServiceList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
