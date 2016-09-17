package com.padc.healthcaredirectory.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.ArticleVO;
import com.padc.healthcaredirectory.views.holders.ArticleViewHolder;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/14/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private LayoutInflater mInflater;
    private List<ArticleVO> mArticleList;
    private ArticleViewHolder.ControllerArticleItem mControllerArticleItem;

    public ArticleAdapter(List<ArticleVO> articleList, ArticleViewHolder.ControllerArticleItem controllerArticleItem) {
        mInflater = LayoutInflater.from(HealthCareDirectoryApp.getContext());
        mArticleList = articleList;
        mControllerArticleItem = controllerArticleItem;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_article, parent, false);
        return new ArticleViewHolder(itemView, mControllerArticleItem);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bindData(mArticleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
