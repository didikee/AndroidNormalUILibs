package com.didikee.uilibs.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.didikee.uilibs.R;


/**
 * Created by didik on 2016/8/12.
 *
 */
public class ProgressShowView extends View {

    private int mColorUp;
    private int mColorDown;

    private int mProgress = 50;

    public ProgressShowView(Context context) {
        super(context);
    }

    public ProgressShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public ProgressShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }

    /**
     *
     * @param context context
     * @param attrs attributes
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressShowView);
        mColorUp = ta.getColor(R.styleable.ProgressShowView_color_up, Color.GRAY);
        mColorDown = ta.getColor(R.styleable.ProgressShowView_color_down, Color.RED);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        //
        //
        int radius = height / 2;

        //
        Paint p = new Paint();

        //
        //
        p.setStyle(Paint.Style.FILL);//
        p.setColor(mColorDown);
        p.setAntiAlias(true);//
        RectF ovalDown = new RectF(0, 0, width, height);//
        canvas.drawRoundRect(ovalDown, radius, radius, p);

        //
        p.setColor(mColorUp);
        int widthUp = width * mProgress / 100;
        RectF ovalUp = new RectF(0, 0, widthUp, height);//
        canvas.drawRoundRect(ovalUp, radius, radius, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("test", "widthMeasureSpec :" + widthMeasureSpec + " || " + "heightMeasureSpec :" +
                heightMeasureSpec);
    }

    /**
     *
     * @param progress progress
     */
    public void setProgress(int progress) {
        mProgress = progress;
    }
}
