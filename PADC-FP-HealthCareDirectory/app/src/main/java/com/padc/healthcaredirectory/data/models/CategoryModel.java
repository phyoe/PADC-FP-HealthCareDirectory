package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.CategoryVO;
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
public class CategoryModel {
    private static final String DUMMY_CATEGORY_LIST = "categories.json";

    private static CategoryModel objInstance;

    private List<CategoryVO> mCategoryList;

    public CategoryModel() {
        super();
        //mCategoryList = initializeCategoryList();
    }

    public static CategoryModel getInstance(){
        if(objInstance == null){
            objInstance = new CategoryModel();
        }
        return objInstance;
    }

    private List<CategoryVO> initializeCategoryList() {
        List<CategoryVO> categoryList = new ArrayList<>();

        try {
            String dummyCategoryList = JsonUtils.getInstance().loadDummyData(DUMMY_CATEGORY_LIST);
            Type listType = new TypeToken<List<CategoryVO>>() {
            }.getType();
            categoryList = CommonInstances.getGsonInstance().fromJson(dummyCategoryList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public List<CategoryVO> getCategoryList() {
        return mCategoryList;
    }

    public CategoryVO getCategoryByTitle(String title) {
        for (CategoryVO category : mCategoryList) {
            if (category.getTitle().equals(title)){
                return category;
            }
        }
        return null;
    }
}
