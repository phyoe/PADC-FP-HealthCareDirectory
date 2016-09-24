package com.padc.healthcaredirectory.data.models;

import com.google.gson.reflect.TypeToken;
import com.padc.healthcaredirectory.data.vos.ArticleVO;
import com.padc.healthcaredirectory.utils.CommonInstance;
import com.padc.healthcaredirectory.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyoe Khant on 9/14/2016.
 */
public class ArticleModel {
    private static final String DUMMY_ARTICLE_LIST = "articles.json";

    private static ArticleModel objInstance;

    private List<ArticleVO> mArticleList;

    public ArticleModel() {
        super();
        mArticleList = initializeArticleList();;
    }

    public static ArticleModel getInstance(){
        if(objInstance == null){
            objInstance = new ArticleModel();
        }
        return objInstance;
    }
    private List<ArticleVO> initializeArticleList() {
        List<ArticleVO> articleList = new ArrayList<>();

        try {
            String dummyArticleList = JsonUtils.getInstance().loadDummyData(DUMMY_ARTICLE_LIST);
            Type listType = new TypeToken<List<ArticleVO>>() {
            }.getType();
            articleList = CommonInstance.getGsonInstance().fromJson(dummyArticleList, listType);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public List<ArticleVO> getArticleList() {
        return mArticleList;
    }

    public ArticleVO getArticleById(int id) {
        for (ArticleVO article : mArticleList) {
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }
}
