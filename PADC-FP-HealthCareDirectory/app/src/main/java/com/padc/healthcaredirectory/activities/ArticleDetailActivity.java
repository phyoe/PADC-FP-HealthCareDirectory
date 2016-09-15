package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.models.ArticleModel;
import com.padc.healthcaredirectory.data.vos.ArticleVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {

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
    private int mArticleId;

    private static final String IE_ARTICLE_ID = "IE_ARTICLE_ID";

    public static Intent newIntent(int articleId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), ArticleDetailActivity.class);
        intent.putExtra(IE_ARTICLE_ID, articleId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mArticleId = getIntent().getIntExtra(IE_ARTICLE_ID, 0);
        mArticle = ArticleModel.getInstance().getArticleById(mArticleId);

        tvArticleTitle.setText(mArticle.getTitle());
        tvArticleDate.setText(mArticle.getArticleDate());
        tvArticleDescription.setText(mArticle.getDescription());
        tvArticleAuthor.setText(mArticle.getAuthor());
        tvArticleWebsite.setText(mArticle.getWebsite());

        Glide.with(imgArticle.getContext())
                .load(R.drawable.article_img)
                .fitCenter()
                .placeholder(R.drawable.article_img)
                .error(R.drawable.article_img)
                .into(imgArticle);
    }

}
