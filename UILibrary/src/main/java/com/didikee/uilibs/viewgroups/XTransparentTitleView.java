package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.didikee.uilibs.R;
import com.didikee.uilibs.utils.UIColor;

/**
 * Created by didik on 2016/10/20.
 */

public class XTransparentTitleView extends RelativeLayout {

    protected View mTitleView;
    protected View mScrollViewInnerLayout;
    private View mStatusView;
    private int mTitleHeight = 0;

    private int mStatusBarColor = Color.GRAY;
    private int mTitleColor = Color.GRAY;
    private XScrollView mScrollView;
    private int mSystemStatusBarHeight;
    private int mLimitHeight = 0;//状态栏透明计算的限制高度
    private OnScrollChangedListener mScrollChangeListener;



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
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTopLayout.setLayoutParams(rlParams);

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
        RelativeLayout.LayoutParams scrollviewParams = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mScrollView.setLayoutParams(scrollviewParams);
        mScrollView.setId(android.R.id.text1);
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

    protected void setActionTitle() {
        mScrollView.setOnScrollListener(new XScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                if (mScrollChangeListener != null) {
                    mScrollChangeListener.onScrollChanged(x, y, oldX, oldY);
                }
                if (mLimitHeight != 0) {
                    if (y <= 0){
                        mStatusView.setBackgroundColor(UIColor.getAlphaColor(0,mStatusBarColor));
                        mTitleView.setBackgroundColor(UIColor.getAlphaColor(0,mTitleColor));
                        setAlphaViews((ViewGroup) mTitleView,0);
                    }else if (y >0 && y <mLimitHeight){
                        float scale = (float) y / mLimitHeight;
                        float alpha = (255 * scale);
                        mStatusView.setBackgroundColor(UIColor.getAlphaColor((int) alpha,mStatusBarColor));
                        mTitleView.setBackgroundColor(UIColor.getAlphaColor((int) alpha,mTitleColor));
                        setAlphaViews((ViewGroup) mTitleView, (int) alpha);
                    }else {
                        mStatusView.setBackgroundColor(mStatusBarColor);
                        mTitleView.setBackgroundColor(mTitleColor);
                        setAlphaViews((ViewGroup) mTitleView,255);
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

    private void setAlphaViews(ViewGroup viewGroup,int alpha){
        if (viewGroup==null){
            return;
        }
        int childCount = viewGroup.getChildCount();
        alpha = alpha < 0 ? 0 : (alpha > 255 ? 255 : alpha);
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            float tempAlpha= (float) (alpha *1.0 /255);
            childAt.setAlpha(tempAlpha);
//            Drawable background = childAt.getBackground();
//            if (background!=null){
//                background.setAlpha(alpha);
//            }
        }
    }

    public void setAlphaTitle(){
    }

    public void initParams(int limitHeight,int statusBarColor,int titleColor){
        this.mLimitHeight=limitHeight;
        this.mStatusBarColor=statusBarColor;
        this.mTitleColor=titleColor;
    }

    public interface OnScrollChangedListener {
        public void onScrollChanged(int x, int y, int oldX, int oldY);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        this.mScrollChangeListener = listener;
    }

}
