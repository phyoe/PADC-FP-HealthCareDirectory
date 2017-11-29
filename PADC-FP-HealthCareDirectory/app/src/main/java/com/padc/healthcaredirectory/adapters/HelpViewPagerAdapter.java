package com.padc.healthcaredirectory.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.padc.healthcaredirectory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyoe Khant on 10/5/2016.
 */
public class HelpViewPagerAdapter extends PagerAdapter {

    // Declare Variables
    Context context;
    int[] mScreenShots;
    LayoutInflater inflater;

    public HelpViewPagerAdapter(Context context, int[] screenShots) {
        this.context = context;
        this.mScreenShots = screenShots;
    }

    @Override
    public int getCount() {
        return mScreenShots.length;
    }

    @Override
    public boolean isViewFromObject(View rootView, Object object) {
        return rootView == ((CardView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        /**
         View itemView = container.getRootView();

         ViewHolder holder;
         if (itemView != null) {
         holder = (ViewHolder) itemView.getTag();
         } else {
         itemView = inflater.inflate(R.layout.view_pager_help, container, false);
         holder = new ViewHolder(itemView);
         itemView.setTag(holder);
         }

         holder.iv_image.setImageResource(mScreenShots[position]);

         if(position == 0) {
         holder.imgLeft.setVisibility(View.GONE);
         holder.imgRight.setVisibility(View.VISIBLE);
         } else if(position == mScreenShots.length-1) {
         holder.imgLeft.setVisibility(View.VISIBLE);
         holder.imgRight.setVisibility(View.GONE);
         } else {
         holder.imgLeft.setVisibility(View.VISIBLE);
         holder.imgRight.setVisibility(View.VISIBLE);
         }

         // Add viewpager_earthquakes_itemhquakes_item.xml to ViewPager
         ((ViewPager) container).addView(itemView);
         /**/

        /**/

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.view_pager_help, container, false);

        ImageView iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        ImageView imgLeft = (ImageView) itemView.findViewById(R.id.iv_left);
        ImageView imgRight = (ImageView) itemView.findViewById(R.id.iv_right);
        // Capture position and set to the ImageView
        iv_image.setImageResource(mScreenShots[position]);

        if(position == 0) {
            imgLeft.setVisibility(View.INVISIBLE);
            imgRight.setVisibility(View.VISIBLE);
        } else if(position == mScreenShots.length-1) {
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.INVISIBLE);
        } else {
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.VISIBLE);
        }
        /**/

        // Add viewpager_earthquakes_itemhquakes_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_earthquake_item.xmlkes_item.xml from ViewPager
        ((ViewPager) container).removeView((CardView) object);

    }

    static class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView iv_image;

        @BindView(R.id.iv_left)
        ImageView imgLeft;

        @BindView(R.id.iv_right)
        ImageView imgRight;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
