package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.adapters.ArticleViewPagerAdapter;
import com.padc.healthcaredirectory.data.models.ArticleModel;
import com.padc.healthcaredirectory.data.vos.ArticleVO;
import com.padc.healthcaredirectory.views.holders.ArticleViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleListActivity extends AppCompatActivity
        implements ArticleViewHolder.ControllerArticleItem {

    LinearLayout earthquake_content_before;
    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    int[] earthquakes;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newInent(){
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), ArticleListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /**
        if (savedInstanceState == null) {
            ArticleListFragment fragment = ArticleListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
        /**/

        //test
        List<ArticleVO> articleList = ArticleModel.getInstance().getArticleList();

        earthquakes = new int[] { R.drawable.dummy_healthcare, R.drawable.dummpy_map,
                R.drawable.dummy_article};
        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager_earthquake);
        // Pass results to ArticleViewPagerAdapter Class
        adapter = new ArticleViewPagerAdapter(this, articleList);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
        ((ViewPager) findViewById(R.id.pager_earthquake)).setAdapter(adapter);
    }

    @Override
    public void onTapArticle(ArticleVO article) {

        Toast.makeText(getApplicationContext(), "Detail View will show ...", Toast.LENGTH_SHORT).show();

        int id = article.getId();

        Intent intent = ArticleDetailActivity.newIntent(id);
        startActivity(intent);

    }
}
