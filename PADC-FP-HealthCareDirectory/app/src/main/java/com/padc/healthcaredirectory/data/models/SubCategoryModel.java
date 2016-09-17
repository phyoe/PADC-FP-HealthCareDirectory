package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.SubCategoryVO;
import com.padc.healthcaredirectory.utils.CommonInstances;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/13/2016.
 */
public class SubCategoryModel {

    private static final String DUMMY_CATEGORY_LIST = "categories.json";

    private static SubCategoryModel objInstance;

    private List<SubCategoryVO> mSubCategoryList;

    public SubCategoryModel() {
        super();
        mSubCategoryList = initializeSubCategoryList();
    }

    public static SubCategoryModel getInstance(){
        if(objInstance == null){
            objInstance = new SubCategoryModel();
        }
        return objInstance;
    }

    private List<SubCategoryVO> initializeSubCategoryList() {
        List<SubCategoryVO> subCategoryList = new ArrayList<>();

        try {
            String dummySubCategoryList = JsonUtils.getInstance().loadDummyData(DUMMY_CATEGORY_LIST);
            Type listType = new TypeToken<List<SubCategoryVO>>() {
            }.getType();
            subCategoryList = CommonInstances.getGsonInstance().fromJson(dummySubCategoryList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subCategoryList;
    }

    public List<SubCategoryVO> getSubCategoryList() {
        return mSubCategoryList;
    }

    public SubCategoryVO getSubCategoryByName(String name) {
        for (SubCategoryVO subCategory : mSubCategoryList) {
            if (subCategory.getName().equals(name)){
                return subCategory;
            }
        }
        return null;
    }
}
