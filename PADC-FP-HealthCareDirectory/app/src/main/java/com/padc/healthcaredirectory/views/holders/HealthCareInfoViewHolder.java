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

    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @BindView(R.id.tv_title)
    TextView tvTitle;

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

        tvTitle.setText(healthCareInfo.getTitle());

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
        mController.onTapHealthCareInfo(mHealthCareInfo);
    }


    public interface ControllerHealthCareInfoItem {
        void onTapHealthCareInfo(HealthCareInfoVO healthCareInfo);
    }
}
