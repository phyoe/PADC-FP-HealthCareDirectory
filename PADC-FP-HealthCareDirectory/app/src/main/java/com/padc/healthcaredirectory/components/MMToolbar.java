package com.padc.healthcaredirectory.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Created by Phyoe Khant on 9/11/2016.
 */
public class MMToolbar extends Toolbar {
    public MMToolbar(Context context) {
        super(context);
        //if (!isInEditMode())
            //MMFontUtils.setMMFont(this);
    }

    public MMToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //if (!isInEditMode())
            //MMFontUtils.setMMFont(this);
    }

    public MMToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //if (!isInEditMode())
            //MMFontUtils.setMMFont(this);
    }
}
