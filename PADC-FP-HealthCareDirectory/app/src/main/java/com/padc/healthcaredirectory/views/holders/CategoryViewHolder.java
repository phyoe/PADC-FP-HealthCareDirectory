package com.padc.healthcaredirectory.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.healthcaredirectory.HealthCareDirectoryApp;
import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.CategoryVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 9/13/2016.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;

    @BindView(R.id.tv_name)
    TextView tvName;

    private ControllerCategoryItem mController;
    private CategoryVO mCategory;

    public CategoryViewHolder(View itemView, ControllerCategoryItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(CategoryVO category) {
        mCategory = category;

        Log.d(HealthCareDirectoryApp.TAG, String.valueOf(category.getCategories()));

        tvName.setText(category.getMmTitle());

        int img = R.drawable.dummy_doctor;

        Glide.with(ivIcon.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.dummy_doctor)
                .error(R.drawable.dummy_doctor)
                .into(ivIcon);
    }
    @Override
    public void onClick(View view) {
        mController.onTapCategory(mCategory);
    }

    public interface ControllerCategoryItem {
        void onTapCategory(CategoryVO category);
     }
}
