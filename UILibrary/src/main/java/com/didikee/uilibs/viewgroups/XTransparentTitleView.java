package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.didikee.uilibs.R;

/**
 * Created by didik on 2016/10/20.
 */

public class XTransparentTitleView extends FrameLayout {

    protected View mTitleView;
    protected View mScrollViewInnerLayout;
    private View mStatusView;
    private int mTitleHeight = 0;

    private int mStatusBarColor = Color.RED;
    private XScrollView mScrollView;
    private int mSystemStatusBarHeight;
    private int mStatusBarLimitHeight = 0;//透明计算的限制高度
    private OnScrollChangedListener mScrollChangeListener;
    private int mTitleLimitHeight;

    public XTransparentTitleView(Context context) {
        super(context);
    }

    public XTransparentTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public XTransparentTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XTransparentTitleView);
        final int titleLayoutID = ta.getResourceId(R.styleable.XTransparentTitleView_TitleLayout,
                -1);
        final int scrollViewInnerLayoutID = ta.getResourceId(R.styleable
                .XTransparentTitleView_ScrollViewInnerLayout, -1);
        if (titleLayoutID == -1 || scrollViewInnerLayoutID == -1) {
            throw new IllegalArgumentException("TitleLayout and ContentLayout cannot be null!");
        }
//        LayoutInflater.from(context).inflate(R.layout.layout_base_framelayout,this);

        //TODO what is this method?
//        isInEditMode()
        mSystemStatusBarHeight = getSystemStatusBarHeight(context);

        //create StatusView start
        LinearLayout mTopLayout = new LinearLayout(context);
        mTopLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams flParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTopLayout.setLayoutParams(flParams);

        mStatusView = new View(context);
        LinearLayout.LayoutParams sParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, mSystemStatusBarHeight);
        mStatusView.setLayoutParams(sParams);
        mStatusView.setBackgroundColor(mStatusBarColor);

        mTopLayout.addView(mStatusView);
        mTitleView = View.inflate(context, titleLayoutID, mTopLayout);
        //create StatusView end

        //inflate inner layout start
        mScrollView = new XScrollView(context);
        FrameLayout.LayoutParams scrollviewParams = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mScrollView.setLayoutParams(scrollviewParams);
        mScrollViewInnerLayout = View.inflate(context, scrollViewInnerLayoutID, mScrollView);
        //inflate inner layout start

        this.addView(mScrollView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        this.addView(mTopLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);

        ta.recycle();
    }

    private int getSystemStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 设置状态栏填充View的背景色
     * @param color
     */
    public void setStatusBarColor(int color) {
        this.mStatusBarColor = color;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mTitleHeight == 0) {
            mTitleHeight = mTitleView.getHeight();
            Log.e("test", "mTitleView Height2:" + mTitleView.getHeight());

            setActionTitle();
        }
    }

    private void setActionTitle() {
        mScrollView.setOnScrollListener(new XScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                if (mScrollChangeListener != null) {
                    mScrollChangeListener.onScrollChanged(x, y, oldX, oldY);
                }
                if (mStatusBarLimitHeight != 0) {
                    if (y <= 0){
                        mStatusView.setBackgroundColor(getAlphaColor(0,mStatusBarColor));
                    }else if (y >0 && y <mTitleHeight){
                        float scale = (float) y / mTitleHeight;
                        float alpha = (255 * scale);
                        mStatusView.setBackgroundColor(getAlphaColor((int) alpha,mStatusBarColor));
                    }else {
                        mStatusView.setBackgroundColor(mStatusBarColor);
                    }
                }
            }
        });
    }

    public void setAlphaColor(int alpha, View... views) {
        alpha = alpha < 0 ? 0 : (alpha > 255 ? 255 : alpha);
        for (View view : views) {
//            Drawable backgroundDrawable = view.getBackground();
//            if (backgroundDrawable instanceof ColorDrawable){
//                int color = ((ColorDrawable) backgroundDrawable).getColor();
//                view.setBackgroundColor(color);
//            }
            view.getBackground().setAlpha(alpha);
        }
    }

    private void setStatusBarLimitHeight(int height){
        this.mStatusBarLimitHeight =height;
        mStatusView.setBackgroundColor(getAlphaColor(0,mStatusBarColor));
    }
    private void setStatusBarLimitHeight(int height,int initStatusBarBackgroundColor){
        this.mStatusBarLimitHeight =height;
        mStatusView.setBackgroundColor(initStatusBarBackgroundColor);
    }

    private void setTitleLimitHeight(int height,int initTitleBackgroundColor){
        this.mTitleLimitHeight=height;
        mTitleView.setBackgroundColor(initTitleBackgroundColor);
    }

    private void setTitleLimitHeight(int height){
        this.mTitleLimitHeight=height;
        mTitleView.getBackground().setAlpha(0);
    }

    public interface OnScrollChangedListener {
        public void onScrollChanged(int x, int y, int oldX, int oldY);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        this.mScrollChangeListener = listener;
    }

    public int getAlphaColor(int alpha, int color) {
        // edited to support big numbers bigger than 0x80000000
//        int color = (int)Long.parseLong(myColorString, 16);
        alpha = alpha < 0 ? 0 : (alpha > 255 ? 255 : alpha);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.argb(alpha, r, g, b);
    }

}
