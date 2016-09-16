package com.padc.healthcaredirectory.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padc.healthcaredirectory.R;
import com.padc.healthcaredirectory.data.vos.ArticleVO;

import java.util.List;

/**
 * Created by Phyoe Khant on 9/15/2016.
 */
public class ArticleViewPagerAdapter extends PagerAdapter {

    Context context;
    private LayoutInflater mInflater;
    private List<ArticleVO> mArticleList;
    private String[] mArticleTitleList;

    public ArticleViewPagerAdapter(Context context, List<ArticleVO> articleList) {
        this.context = context;
        mArticleList = articleList;
        mArticleTitleList = this.getTitleList();
    }

    @Override
    public int getCount() {
        return mArticleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((CardView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Declare Variables
        TextView tv_title;

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.view_item_article, container,
                false);

        // Locate the ImageView in viewpager_earthquakes_itemhquakes_item.xml
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        // Capture position and set to the ImageView
        tv_title.setText(mArticleTitleList[position]);

        // Add viewpager_earthquakes_itemhquakes_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_earthquake_item.xmlkes_item.xml from ViewPager
        ((ViewPager) container).removeView((CardView) object);
    }

    private String[] getTitleList() {

        String[] titleList = new String[0];
        int i = 0;
        for(ArticleVO article : mArticleList){
            titleList[i] = article.getTitle();
            i++;
        }
        return titleList;
    }
}
