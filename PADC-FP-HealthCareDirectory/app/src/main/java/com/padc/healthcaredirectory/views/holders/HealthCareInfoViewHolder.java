package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 9/23/2016.
 */
public class HealthCareInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.iv_author)
    ImageView ivAuthor;

    @BindView(R.id.tv_author_name)
    TextView tvAuthorName;

    @BindView(R.id.tv_article_date)
    TextView tvArticleDate;

    @BindView(R.id.tv_article_title)
    TextView tvArticleTitle;

    @BindView(R.id.tv_article_description)
    TextView tvArticleDescription;

    @BindView(R.id.iv_article)
    ImageView ivArticle;

    @BindView(R.id.tv_article_link)
    TextView tvArticleLink;

    private ControllerHealthCareInfoItem mController;
    private HealthCareInfoVO mHealthCareInfo;

    public HealthCareInfoViewHolder(View itemView, ControllerHealthCareInfoItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(HealthCareInfoVO healthCareInfo) {
        mHealthCareInfo = healthCareInfo;

        //Log.d(HealthCareDirectoryApp.TAG, healthCareInfo.getTitle());

        tvAuthorName.setText(healthCareInfo.getAuthor().getAuthorName());
        tvArticleDate.setText(healthCareInfo.getPublishedDate());
        tvArticleTitle.setText(healthCareInfo.getTitle());
        tvArticleDescription.setText(healthCareInfo.getShortDescription());
        tvArticleLink.setText(healthCareInfo.getCompleteUrl());

        String author_pic = healthCareInfo.getAuthor().getAuthorPicture();
        Glide.with(ivAuthor.getContext())
                .load(author_pic)
                .centerCrop()
                .placeholder(R.drawable.dummy_avatar)
                .error(R.drawable.dummy_avatar)
                .into(ivAuthor);

        String article_pic = healthCareInfo.getImage();
        Glide.with(ivArticle.getContext())
                .load(article_pic)
                .centerCrop()
                .placeholder(R.drawable.healthcare_photo_placeholder)
                .error(R.drawable.healthcare_photo_placeholder)
                .into(ivArticle);
    }

    @Override
    public void onClick(View view) {
        mController.onTapHealthCareInfo(mHealthCareInfo);
    }


    public interface ControllerHealthCareInfoItem {
        void onTapHealthCareInfo(HealthCareInfoVO healthCareInfo);
    }
}
