package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.ArticleVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 9/14/2016.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ControllerArticleItem mController;
    private ArticleVO mArticle;

    public ArticleViewHolder(View itemView, ControllerArticleItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }
    public void bindData(ArticleVO article) {
        mArticle = article;

        Log.d(HealthCareDirectoryApp.TAG, article.getTitle());

        tvTitle.setText(article.getTitle());

        int img = R.drawable.dummy_article;
        Glide.with(ivIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.dummy_article)
                .error(R.drawable.dummy_article)
                .into(ivIcon);
    }

    @Override
    public void onClick(View view) {
        mController.onTapArticle(mArticle);
    }

    public interface ControllerArticleItem {
        void onTapArticle(ArticleVO article);
    }
}
