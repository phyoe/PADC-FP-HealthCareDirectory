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
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.HealthCareServiceViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClinicListFragment extends BaseFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_clinics)
    RecyclerView rvClinics;

    private HealthCareServiceAdapter mHealthCareServiceAdapter;
    private HealthCareServiceViewHolder.ControllerHealthCareItem mControllerHealthCareServiceItem;

    public static ClinicListFragment newInstance() {
        ClinicListFragment fragment = new ClinicListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HealthCareServiceViewHolder.ControllerHealthCareItem) {
            mControllerHealthCareServiceItem = (HealthCareServiceViewHolder.ControllerHealthCareItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
        ButterKnife.bind(this, rootView);

        List<HealthCareServiceVO> healthCareList = HealthCareServiceModel.getInstance().getHealthCareServiceList();

        mHealthCareServiceAdapter = new HealthCareServiceAdapter(healthCareList, mControllerHealthCareServiceItem);
        rvClinics.setAdapter(mHealthCareServiceAdapter);

        rvClinics.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //For Persistence Layer
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoadedBroadcastReceiver, new IntentFilter(HealthCareServiceModel.BROADCAST_DATA_LOADED));
    }

    @Override
    public void onStop() {
        super.onStop();
        //For Persistence Layer
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDataLoadedBroadcastReceiver);
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
                long service_id = healthCareService.getHealthCareId();

                if (healthCareService.getCategory().contains(HealthCareDirectoryConstants.STR_CLINIC)) {

                    healthCareService.setPhones(HealthCareServiceVO.loadHealthCareServicePhoneByServiceId(service_id));
                    healthCareService.setFax(HealthCareServiceVO.loadHealthCareServiceFaxByServiceId(service_id));
                    healthCareService.setTags(HealthCareServiceVO.loadHealthCareServiceTagsByServiceId(service_id));
                    healthCareService.setOperations(HealthCareServiceVO.loadHealthCareServiceOperationsByServiceId(service_id));

                    healthCareServiceList.add(healthCareService);
                }
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
