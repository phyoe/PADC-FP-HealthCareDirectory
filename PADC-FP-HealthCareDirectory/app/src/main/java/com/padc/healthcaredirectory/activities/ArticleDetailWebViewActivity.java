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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.persistence.HealthCareContract;
import com.padc.healthcaredirectory.data.vos.HealthCareInfoVO;
import com.padc.healthcaredirectory.utils.HealthCareDirectoryConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailWebViewActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.wv_web)
    WebView wvWeb;

    private long mHealthCareInfoId;
    private HealthCareInfoVO mHealthCareInfo;

    private static final String IE_INFO_ID = "IE_INFO_ID";

    public static Intent newIntent(long infoId) {
        Intent intent = new Intent(HealthCareDirectoryApp.getContext(), ArticleDetailWebViewActivity.class);
        intent.putExtra(IE_INFO_ID, infoId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                Toast.makeText(HealthCareDirectoryApp.getContext(), getString(R.string.lbl_share), Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_web_view);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mHealthCareInfoId = getIntent().getLongExtra(IE_INFO_ID, 0);
        getSupportLoaderManager().initLoader(HealthCareDirectoryConstants.HEALTHCARE_INFO_DETAIL_WEBVIEW_LOADER, null, this);

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
        String url = healthCareInfo.getCompleteUrl();

        Log.e("WebView URL : ", url);

        // Websettings to setup the webview
        WebSettings webSettings = wvWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCachePath(this.getCacheDir().getAbsolutePath());
        webSettings.setAppCacheEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        wvWeb.setWebChromeClient(new WebChromeClient());

        wvWeb.requestFocus(View.FOCUS_DOWN);
        wvWeb.setFocusable(true);

        wvWeb.loadUrl(url);
    }

}
