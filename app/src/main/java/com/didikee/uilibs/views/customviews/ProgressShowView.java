package com.didikee.uilibs.views.customviews;

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
 * 显示进度
 */
public class ProgressShowView extends View {

    private int mColorUp;//上层的颜色
    private int mColorDown;//下层的颜色

    private int mProgress = 50;//默认值

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
     * 获取自定义属性的值
     * @param context
     * @param attrs
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
        //单位是dp而非px
        //角度
        int radius = height / 2;

        // 创建画笔
        Paint p = new Paint();

        //画圆角矩形
        //画下层
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(mColorDown);
        p.setAntiAlias(true);// 设置画笔的锯齿效果,true表示抗锯齿,false不需要处理
        RectF ovalDown = new RectF(0, 0, width, height);// 设置个新的长方形
        canvas.drawRoundRect(ovalDown, radius, radius, p);

        //画上层
        p.setColor(mColorUp);
        int widthUp = width * mProgress / 100;
        RectF ovalUp = new RectF(0, 0, widthUp, height);// 设置个新的长方形
        canvas.drawRoundRect(ovalUp, radius, radius, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("test", "widthMeasureSpec :" + widthMeasureSpec + " || " + "heightMeasureSpec :" +
                heightMeasureSpec);
    }

    /**
     * 设置百分比 98% 输入98 即可
     * @param progress
     */
    public void setProgress(int progress) {
        mProgress = progress;
    }
}
