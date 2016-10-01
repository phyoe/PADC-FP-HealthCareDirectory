package com.padc.healthcaredirectory.activities;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;

import com.padc.healthcaredirectory.R;

/**
 * Created by Phyoe Khant on 9/29/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected void sendViaShareIntent(String msg) {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(BaseActivity.this)
                .setType("text/plain")
                .setText(msg)
                .getIntent(), getString(R.string.lbl_share)));
    }
}
