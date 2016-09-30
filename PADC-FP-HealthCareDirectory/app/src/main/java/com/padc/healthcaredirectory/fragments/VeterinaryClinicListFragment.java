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
import com.padc.healthcaredirectory.adapters.VeterinaryClinicAdapter;
import com.padc.healthcaredirectory.data.vos.VeterinaryClinicVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;
import com.padc.healthcaredirectory.views.holders.VeterinaryClinicViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saw Yu Nwe on 9/29/2016.
 */
public class VeterinaryClinicListFragment extends BaseFragment {

    private static String mCurrentClick;

    @BindView(R.id.rv_veterinary_clinic)
    RecyclerView rvVeterinaryClinic;



    private VeterinaryClinicAdapter mVeterinaryClinicAdapter;
    private VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem controllerVeterinaryClinicItem;

    public static VeterinaryClinicListFragment newInstance() {
        VeterinaryClinicListFragment fragment = new VeterinaryClinicListFragment();
        return fragment;
    }

    public static VeterinaryClinicListFragment newInstance(String currentClick) {
        mCurrentClick=currentClick;
        VeterinaryClinicListFragment fragment = new VeterinaryClinicListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_veterinary_clinic_list, container, false);
        ButterKnife.bind(this, rootView);

        List<VeterinaryClinicVO> veterinaryClinicList = setTempClinicData();

        //List<HealthCareVO> healthCareList = HealthCareModel.getInstance().getHealthCareList();

        switch (mCurrentClick)
        {
            case HealthCareDirectoryConstants.STR_VET_CLINIC:
                veterinaryClinicList=setTempClinicData();
                break;
            case HealthCareDirectoryConstants.STR_VET_EQUIPMENT:
                veterinaryClinicList=setTempEquipmentData();
                break;
            case HealthCareDirectoryConstants.STR_VET_MEDICINE:
                veterinaryClinicList=setTempMedicineData();
                break;
            case HealthCareDirectoryConstants.STR_VET_SPA:
                veterinaryClinicList=setTempSpaData();
                break;

        }


        mVeterinaryClinicAdapter = new VeterinaryClinicAdapter(veterinaryClinicList,controllerVeterinaryClinicItem,mCurrentClick);
        rvVeterinaryClinic.setAdapter(mVeterinaryClinicAdapter);

        rvVeterinaryClinic.setLayoutManager(new GridLayoutManager(getContext(), super.gridColumnSpanCount));
        return rootView;
    }

    protected List<VeterinaryClinicVO> setTempClinicData(){
        List<VeterinaryClinicVO> veterinaryClinicList = new ArrayList<>();


        for(int i=0 ; i<10 ; i++){

            String[] phones={"+95 9 526 2313"};
            String[] images={"veterinary_clinic2"};

            VeterinaryClinicVO veterinaryClinic=new VeterinaryClinicVO();
            veterinaryClinic.setId(001);
            veterinaryClinic.setName("ျပည္");
            veterinaryClinic.setAddress("အမွတ္ (၄၇၁)၊ ျပည္လမ္းမၾကီး၊ ကမာရြတ္ ျမိဳ႕နယ္၊ ရန္ကုန္ျမိဳ႕။");

            veterinaryClinic.setPhones(phones);
            veterinaryClinic.setWebsite("https://www.pyayvetclinic.com/");
            veterinaryClinic.setEmail("pyayvetclinic@gmail.com");
            veterinaryClinic.setFacebook("www.facebook.com/pyay-vet-clinic");
            veterinaryClinic.setMapinfo("MapInfo");
            veterinaryClinic.setStockPhotoPath(images);
            veterinaryClinicList.add(veterinaryClinic);
        }
        return veterinaryClinicList;
    }

    protected List<VeterinaryClinicVO> setTempEquipmentData(){
        List<VeterinaryClinicVO> veterinaryClinicList = new ArrayList<>();


        for(int i=0 ; i<10 ; i++){

            String[] phones={"09421071288"};
            String[] images={"veterinary_equipment_icon"};

            VeterinaryClinicVO veterinaryClinic=new VeterinaryClinicVO();
            veterinaryClinic.setId(002);
            veterinaryClinic.setName("ေမလိခ");
            veterinaryClinic.setAddress("အမွတ္ (၃၆၀ ခ)၊ ၀ိုင္ယာလက္ ၁ လမ္း၊ ကမာၻေအးဘုရားလမ္း၊ မရမ္းကုန္းျမိဳ႕နယ္");

            veterinaryClinic.setPhones(phones);
            veterinaryClinic.setWebsite("https://www.maylikhaveterinaryclinic.com/");
            veterinaryClinic.setEmail("maylikhavetclinic@gmail.com");
            veterinaryClinic.setFacebook("May Li Kha Veterinary Clinic");
            veterinaryClinic.setMapinfo("MapInfo");
            veterinaryClinic.setStockPhotoPath(images);
            veterinaryClinicList.add(veterinaryClinic);
        }
        return veterinaryClinicList;
    }

    protected List<VeterinaryClinicVO> setTempMedicineData(){
        List<VeterinaryClinicVO> veterinaryClinicList = new ArrayList<>();


        for(int i=0 ; i<10 ; i++){

            String[] phones={"095014606"};
            String[] images={"veterinary_medicine_shop_icon"};

            VeterinaryClinicVO veterinaryClinic=new VeterinaryClinicVO();
            veterinaryClinic.setId(003);
            veterinaryClinic.setName("မင္း");
            veterinaryClinic.setAddress("အမွတ္ (၇)၊ ျမင့္မိုရ္လမ္း၊ စမ္းေခ်ာင္းျမိဳ႕နယ္");

            veterinaryClinic.setPhones(phones);
            veterinaryClinic.setWebsite("https://www.minvetclinic.com/");
            veterinaryClinic.setEmail("minvetclinic@gmail.com");
            veterinaryClinic.setFacebook("www.facebook.com/min-vet-clinic");
            veterinaryClinic.setMapinfo("MapInfo");
            veterinaryClinic.setStockPhotoPath(images);
            veterinaryClinicList.add(veterinaryClinic);
        }
        return veterinaryClinicList;
    }

    protected List<VeterinaryClinicVO> setTempSpaData(){
        List<VeterinaryClinicVO> veterinaryClinicList = new ArrayList<>();


        for(int i=0 ; i<10 ; i++){

            String[] phones={"09262190500"};
            String[] images={"veterinary_medicine_spa_icon"};

            VeterinaryClinicVO veterinaryClinic=new VeterinaryClinicVO();
            veterinaryClinic.setId(004);
            veterinaryClinic.setName("Diamond Apex Pet Care Center");
            veterinaryClinic.setAddress("အမွတ္(၃၇)၊ ေအာင္ေဇယ်လမ္း၊ ေက်ာက္ကုန္း၊ ရန္ကင္းျမိဳ႕နယ္္");

            veterinaryClinic.setPhones(phones);
            veterinaryClinic.setWebsite("https://www.diamondapexpetcare.com/");
            veterinaryClinic.setEmail("diamondapexpetcare@gmail.com");
            veterinaryClinic.setFacebook("www.facebook.com/diamond-apex-pet-care-center");
            veterinaryClinic.setMapinfo("MapInfo");
            veterinaryClinic.setStockPhotoPath(images);
            veterinaryClinicList.add(veterinaryClinic);
        }
        return veterinaryClinicList;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem){
            controllerVeterinaryClinicItem = (VeterinaryClinicViewHolder.ControllerVeterinaryClinicItem) context;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }
}
