package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by didik on 2016/8/18.
 */
public class NormalItemView extends FrameLayout {

    private Context mContext;

    public NormalItemView(Context context) {
        super(context);
    }

    public NormalItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NormalItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        this.mContext=context;
        bindView();
        getAttrs(attrs);

    }

    private void bindView() {
//        LayoutInflater.from(mContext).inflate(R.layout.)
    }

    private void getAttrs(AttributeSet attrs) {

    }
}
