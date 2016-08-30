package com.didikee.uilibs.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.didikee.uilibs.R;


/**
 * Created by didik on 2016/8/17.
 */
public class XTextView extends TextView {
    public static final int DRAWABLE_LEFT = 0;
    public static final int DRAWABLE_TOP = 1;
    public static final int DRAWABLE_RIGHT = 2;
    public static final int DRAWABLE_BOTTOM = 3;
    private Drawable mInnerBackground;
    private OnDrawableClickListener mListener;


    public XTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }

    public XTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public XTextView(Context context) {
        super(context);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XEditText);
        mInnerBackground = ta.getDrawable(R.styleable.XEditText_inner_background);
        ta.recycle();
    }

    public interface OnDrawableClickListener {
        /**
         *
         * @param who DRAWABLE_LEFT = 0
         *            DRAWABLE_TOP = 1
         *            DRAWABLE_RIGHT = 2
         *            DRAWABLE_BOTTOM = 3
         */
        void onDrawableClick(XTextView view, int who);
    }
    public void setOnDrawableClickListener(OnDrawableClickListener listener){
        this.mListener=listener;
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    //right
                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                    if (drawableRight != null && event.getX() >= (measuredWidth - drawableRight
                            .getBounds().width())) {
                        mListener.onDrawableClick(this, DRAWABLE_RIGHT);
                        return true;
                    }
                    //left
                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
                    int offsetLeft = drawableLeft.getBounds().width();
                    if (drawableLeft != null && event.getX() <= offsetLeft) {
                        mListener.onDrawableClick(this, DRAWABLE_LEFT);
                        return true;
                    }

                    //top
                    Drawable drawableTop = getCompoundDrawables()[DRAWABLE_TOP];
                    int offset = getTop() + drawableTop.getBounds().height();
                    if (drawableTop != null && event.getY() <= drawableTop.getBounds().height()
                            && event.getY() >= 0) {
                        mListener.onDrawableClick(this, DRAWABLE_TOP);
                        return true;
                    }

                    //bottom
                    Drawable drawableBottom = getCompoundDrawables()[DRAWABLE_BOTTOM];
                    int offsetBottom = measuredHeight - drawableBottom.getBounds().height();
                    if (drawableBottom != null && event.getY() >= offset) {
                        mListener.onDrawableClick(this, DRAWABLE_BOTTOM);
                        return true;
                    }

                }
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mInnerBackground != null) {

            int scrollX = getScrollX();
            int measuredHeight = getMeasuredHeight();
            int measuredWidth = getMeasuredWidth();

            int drawablePadding = getCompoundDrawablePadding();

            //(x,y)
            Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
            int x = drawableLeft.getBounds().width();
            Drawable drawableTop = getCompoundDrawables()[DRAWABLE_TOP];
            int y = drawableTop.getBounds().height();

            //end (_x,_y)
            Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
            int x2 = drawableRight.getBounds().width();
            Drawable drawableBottom = getCompoundDrawables()[DRAWABLE_BOTTOM];
            int y2 = drawableBottom.getBounds().height();
            int _x = measuredWidth - x2;
            int _y = measuredHeight - y2;


            mInnerBackground.setBounds(scrollX + x + drawablePadding, y + drawablePadding, scrollX + _x -
                    drawablePadding, _y - drawablePadding);
            mInnerBackground.draw(canvas);
        }
    }

    /**
     *
     * @param background background
     */
    public void setInnerBackground(Drawable background) {
        this.mInnerBackground = background;
    }
}
