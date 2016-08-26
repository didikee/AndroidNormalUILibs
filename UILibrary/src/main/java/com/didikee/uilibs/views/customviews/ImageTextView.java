package com.didikee.uilibs.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.didikee.uilibs.R;
import com.didikee.uilibs.utils.DisplayUtil;


/**
 * Created by didik on 2016/8/23.
 * imageview with text
 *
 * note: text length must be less!!! text width must be smaller than View's width.
 */
public class ImageTextView extends ImageView {

    private Context mContext;
    private Paint mPaint;

    private String mText="Hello";
    private boolean mBold =false;
    private int mGravity= CENTER;
    private int mColor= Color.BLACK;

    public static final int LEFT=0;
    public static final int CENTER=1;
    public static final int RIGHT=2;
    private float mTextSize=14;

    public ImageTextView(Context context) {
        this(context,null);
    }



    public ImageTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        initImageTextView(context,attrs);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageTextView(context,attrs);
    }

    private void initImageTextView(Context context, AttributeSet attrs) {
        this.mContext=context;
        mPaint=new Paint();
        getAttrs(attrs);
        this.setClickable(true);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ImageTextView);
        mTextSize= typedArray.getDimension(R.styleable.ImageTextView_textSize, 14);
        mText=typedArray.getString(R.styleable.ImageTextView_text);
        mBold=typedArray.getBoolean(R.styleable.ImageTextView_bold,false);
        mGravity=typedArray.getInt(R.styleable.ImageTextView_textGravity,1);
        mColor=typedArray.getColor(R.styleable.ImageTextView_textColor,Color.BLACK);
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int textSize = DisplayUtil.sp2px(mContext, mTextSize);
        mPaint.setTextSize(textSize);

        if (mGravity==LEFT){
            mPaint.setTextAlign(Paint.Align.LEFT);
        }else if (mGravity==CENTER){
            mPaint.setTextAlign(Paint.Align.CENTER);
        }else if (mGravity==RIGHT){
            mPaint.setTextAlign(Paint.Align.RIGHT);
        }

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        float baseline = (measuredHeight - fontMetrics.bottom - fontMetrics.top) / 2;
        //is bold
        mPaint.setFakeBoldText(mBold);
        if (mGravity==LEFT){
            canvas.drawText(mText, 0, baseline, mPaint);
        }else if (mGravity==CENTER){
            canvas.drawText(mText, measuredWidth/2, baseline, mPaint);
        }else if (mGravity==RIGHT){
            canvas.drawText(mText, measuredWidth, baseline, mPaint);
        }

    }

    /**
     * set text
     * @param txt view Content
     */
    public void setText(String txt){
        mText=txt;
    }

    /**
     * 设置是否加粗
     * @param isBold false为normal
     */
    public void setTextBold(boolean isBold){
        mBold=isBold;
    }

    /**
     * 设置文字颜色
     * @param color integer
     */
    public void setTextColor(int color){
        mColor=color;
    }

    /**
     * 设置字体大小
     * @param sp 尺寸
     */
    public void setTextSize(float sp){
        mTextSize=sp;
    }

    /**
     * 设置文字显示的位置
     * @param gravity 只支持左中右(0,1,2)
     */
    public void setGravity(int gravity){
        if (gravity!=LEFT && gravity!=RIGHT){
            mGravity=CENTER;
        }else {
            mGravity=gravity;
        }
    }
}
