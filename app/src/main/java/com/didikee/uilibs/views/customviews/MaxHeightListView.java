package com.didikee.uilibs.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by didik on 2016/8/12.
 * 最大高度的listview
 */
public class MaxHeightListView extends ListView {

    public MaxHeightListView(Context context) {
        super(context);
    }

    public MaxHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxHeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
