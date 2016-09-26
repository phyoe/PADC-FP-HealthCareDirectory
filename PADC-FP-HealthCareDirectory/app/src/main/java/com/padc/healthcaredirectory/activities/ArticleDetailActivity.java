package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.ArticleVO;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_article)
    ImageView imgArticle;

    @BindView(R.id.tv_article_title)
    TextView tvArticleTitle;

    @BindView(R.id.tv_article_date)
    TextView tvArticleDate;

    @BindView(R.id.tv_article_description)
    TextView tvArticleDescription;

    @BindView(R.id.tv_article_author)
    TextView tvArticleAuthor;

    @BindView(R.id.tv_article_website)
    TextView tvArticleWebsite;

    private ArticleVO mArticle;
    private long mHealthCareInfoId;

    private HealthCareInfoVO mHealthCareInfo;

    private static final String IE_INFO_ID = "IE_INFO_ID";

    public static Intent newIntent(long infoId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), ArticleDetailActivity.class);
        intent.putExtra(IE_INFO_ID, infoId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mHealthCareInfoId = getIntent().getLongExtra(IE_INFO_ID, 0);

        //Data retrieve from Offline Json
        /**
        mArticle = ArticleModel.getInstance().getArticleById(mArticleId);
        tvArticleTitle.setText(mArticle.getTitle());
        tvArticleDate.setText(mArticle.getArticleDate());
        tvArticleDescription.setText(mArticle.getDescription());
        tvArticleAuthor.setText(mArticle.getAuthor());
        tvArticleWebsite.setText(mArticle.getWebsite());
        /**/

        /**
        //Data retrieve from Network Layer
        mHealthCareInfo = HealthCareInfoModel.getInstance().getHealthCareInfoById(mHealthCareInfoId);
        tvArticleTitle.setText(mHealthCareInfo.getTitle());
        tvArticleDate.setText(mHealthCareInfo.getPublishedDate());
        tvArticleDescription.setText(mHealthCareInfo.getShortDescription());
        tvArticleAuthor.setText(mHealthCareInfo.getAuthor().getAuthorName());
        tvArticleWebsite.setText(mHealthCareInfo.getCompleteUrl());

        //String imageUrl = mHealthCareInfo.getimgUrl();
        Glide.with(imgArticle.getContext())
                .load(R.drawable.article_img) //.load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.article_img)
                .error(R.drawable.article_img)
                .into(imgArticle);
        /**/

        //For Persistence Layer
        getSupportLoaderManager().initLoader(HealthCareDirectoryConstants.HEALTHCARE_INFO_DETAIL_LOADER, null, this);
    }


    /**
     * For Persistence Layer
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                HealthCareContract.HealthCareInfoEntry.buildHealthCareInfoUriWithInfoId(mHealthCareInfoId),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        boolean findData = false;
        if (data != null && data.moveToFirst()) {
            while (data.moveToNext()) {
                if (!findData) {
                    mHealthCareInfo = HealthCareInfoVO.parseFromCursor(data);
                    mHealthCareInfo.setAuthor(HealthCareInfoVO.loadHealthCareInfoAuthorByInfoId(mHealthCareInfoId));

                    if (mHealthCareInfo.getId() == mHealthCareInfoId) {
                        bindData(mHealthCareInfo);
                        findData = true;
                    }
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    private void bindData(HealthCareInfoVO healthCareInfo) {

        //Data retrieve from Persistence Layer
        tvArticleTitle.setText(healthCareInfo.getTitle());
        tvArticleDate.setText(healthCareInfo.getPublishedDate());
        tvArticleDescription.setText(healthCareInfo.getShortDescription());
        tvArticleAuthor.setText(healthCareInfo.getAuthor().getAuthorName());
        tvArticleWebsite.setText(healthCareInfo.getCompleteUrl());

        //String imageUrl = mHealthCareInfo.getimgUrl();
        Glide.with(imgArticle.getContext())
                .load(R.drawable.article_img) //.load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.article_img)
                .error(R.drawable.article_img)
                .into(imgArticle);

    }
}
