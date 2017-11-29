package com.padc.healthcaredirectory.activities;



import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiseaseDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String IE_INFO_ID = "IE_INFO_ID";

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public static Intent newIntent(long infoId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), DiseaseDetailActivity.class);
        intent.putExtra(IE_INFO_ID, infoId);
        return intent;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

