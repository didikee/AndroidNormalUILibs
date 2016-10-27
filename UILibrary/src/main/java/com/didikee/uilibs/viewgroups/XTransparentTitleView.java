package com.didikee.uilibs.viewgroups;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
    private View mBottomView;
    private GestureDetector mGestureDetector;
    private ObjectAnimator animator;
    private int mBottomHeight;


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
        final int bottomLayoutID = ta.getResourceId(R.styleable
                .XTransparentTitleView_BottomLayout, -1);
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
        mScrollView.setId(android.R.id.text1);
        RelativeLayout.LayoutParams scrollviewParams = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        scrollviewParams.addRule(RelativeLayout.ABOVE,android.R.id.text2);
        mScrollView.setLayoutParams(scrollviewParams);
        mScrollViewInnerLayout = View.inflate(context, scrollViewInnerLayoutID, mScrollView);

        if (bottomLayoutID!=-1){
            mBottomView = LayoutInflater.from(context).inflate(bottomLayoutID,null);
            mBottomView.setId(android.R.id.text2);
            RelativeLayout.LayoutParams btParams = new LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            btParams.addRule(RelativeLayout.BELOW,android.R.id.text1);
//            btParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mBottomView.setLayoutParams(btParams);
        }
        //inflate inner layout start
        RelativeLayout.LayoutParams scParams = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scParams.addRule(RelativeLayout.ABOVE,android.R.id.text2);

        RelativeLayout.LayoutParams btParams = new LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        btParams.addRule(RelativeLayout.BELOW,android.R.id.text1);
        btParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        this.addView(mScrollView,scParams);
        this.addView(mBottomView,btParams);
//        this.addView(mScrollView,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        this.addView(mBottomView,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.addView(mTopLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);

        ta.recycle();

        mGestureDetector = new GestureDetector(context, scroller);
    }

    private int getSystemStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 设置状态栏填充View的背景色
     * @param color color
     */
    public void setStatusBarColor(int color) {
        this.mStatusBarColor = color;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mTitleHeight == 0) {
            mTitleHeight = mTitleView.getHeight();
            if (mBottomView!=null ){
                mBottomHeight = mBottomView.getHeight();
            }
            Log.e("test", "mTitleView Height2:" + mTitleView.getHeight());

            setActionTitle();
        }
    }

    protected void setActionTitle() {
        mScrollView.setOnScrollListener(new XScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                Log.e("tes","y: "+y+"   oldY: "+oldY);
                //oldY < Y 向下
                //oldY > Y 向上
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


        mScrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downY= event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mBottomView!=null){
                            if (canAnimate){
                                moveY=event.getY();
                                float dY = moveY - downY;
                                int height = mBottomView.getHeight();
                                if (dY > 100 && height==mBottomHeight){
                                    Log.e("test","向下");
                                    mBottomView.clearAnimation();
                                    animateYDown(mBottomView);
                                    canAnimate=false;
                                    mScrollView.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            canAnimate=true;
                                        }
                                    },1000);
                                }else if (dY < -100 && height==0){
                                    mBottomView.clearAnimation();
                                    animateYUP(mBottomView);
                                    canAnimate=false;
                                    mScrollView.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            canAnimate=true;
                                        }
                                    },1000);
                                    Log.e("test","向上");
                                }
                            }
                        }


                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
    private boolean canAnimate =true;

    private void animateYUP(final View target){
        if (animator!=null && animator.isRunning() || mBottomHeight==0){
            return;
        }
        animator = ObjectAnimator.ofInt(target, "wwh", 0, mBottomHeight);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height= v;
                target.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
    private void animateYDown(final View target){
        if ((animator!=null && animator.isRunning()) || mBottomHeight==0){
            return;
        }
        animator = ObjectAnimator.ofInt(target, "wwh", mBottomHeight, 0);

        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height= v;
                target.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }

    float downY=0;
    float moveY=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY= event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY=event.getY();
                float dY = moveY - downY;
                if (dY > 100){
                    Log.e("test","111");
                }else if (dY < -100){
                    Log.e("test","222");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener scroller=new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

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
