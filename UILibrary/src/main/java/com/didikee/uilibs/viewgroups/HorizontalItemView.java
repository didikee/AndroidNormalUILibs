package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.didikee.uilibs.R;
import com.didikee.uilibs.utils.DisplayUtil;

/**
 * Created by didik on 2016/8/18.
 */
public class HorizontalItemView extends FrameLayout {

    private int index=0;
    private int leftImageView;
    private int leftTextView;
    private int rightImageView;
    private int rightTextView;
    private int line;


    private Context mContext;

    public HorizontalItemView(Context context) {
        super(context);
        init(context,null,0);
    }

    public HorizontalItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public HorizontalItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public void init(Context context,AttributeSet attrs,int defStyleAttr){
        this.mContext=context;
        getAttrs(attrs);
        //defStyleAttr not used
    }

    /**
     * left imageview
     * @param width  -1:match_parent -2:wrap_content need px
     * @param height -1:match_parent -2:wrap_content need px
     * @param drawableId res id
     */
    public void setLeftImageView(int width,int height,int drawableId,int marginLeft){
        //添加左边的ImageView
        ImageView leftImg=new ImageView(mContext);
        LayoutParams leftImageParams=new LayoutParams(width,height, Gravity.LEFT|Gravity.CENTER_VERTICAL);
        leftImg.setImageResource(drawableId);
        leftImageParams.setMargins(marginLeft,0,0,0);
        addView(leftImg,index,leftImageParams);
        leftImageView=index;
        index++;
    }

    /**
     * right imageview
     * @param width  -1:match_parent -2:wrap_content
     * @param height -1:match_parent -2:wrap_content
     * @param drawableId res id
     */
    public void setRightImageView(int width,int height,int drawableId,int marginRight){
        //添加右边的ImageView
        ImageView rightImg=new ImageView(mContext);
        LayoutParams righImageParams=new LayoutParams(width,height, Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        rightImg.setImageResource(drawableId);
        righImageParams.setMargins(0,0,marginRight,0);
        addView(rightImg,index,righImageParams);
        rightImageView=index;
        index++;
    }

    /**
     * left textview
     * @param txt text content
     * @param textColor color
     * @param textSize textSize
     * @param marginLeft margin left to parent
     */
    public void setLeftTextView(String txt,int textColor,float textSize,int marginLeft){
        //添加左边的Textview
        TextView leftTv=new TextView(mContext);
        LayoutParams leftTvParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,Gravity.LEFT|Gravity.CENTER_VERTICAL);
        leftTv.setText(TextUtils.isEmpty(txt)?"":txt);
        leftTv.setTextColor(textColor);
        leftTv.setTextSize(textSize<=0?12:textSize);
        leftTvParams.setMargins(marginLeft,0,0,0);
        addView(leftTv,index,leftTvParams);
        leftTextView=index;
        index++;
    }

    /**
     * right textview
     * @param txt text content
     * @param textColor color
     * @param textSize textSize need sp
     * @param marginRight margin left to parent
     */
    public void setRightTextView(String txt,int textColor,float textSize,int marginRight){
        //添加左边的Textview
        TextView rightTv=new TextView(mContext);
        LayoutParams rightTvParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        rightTv.setText(TextUtils.isEmpty(txt)?"":txt);
        rightTv.setTextColor(textColor);
        rightTv.setTextSize(textSize<=0?12: textSize);
        rightTvParams.setMargins(0,0,marginRight,0);
        addView(rightTv,index,rightTvParams);
        rightTextView=index;
        index++;
    }

    /**
     *
     * @param gravity location
     * @param color color
     * @param strokeWidthPx stroke width eg:1px
     * @param marginStart margin start
     * @param marginEnd margin end
     */
    public void setLine(int gravity,int color,int strokeWidthPx,int marginStart,int marginEnd) {
        //添加分隔线
        View line=new View(mContext);
        LayoutParams lineParams=null;
//        marginStart=DisplayUtil.dp2px(mContext,marginStart);
//        marginEnd=DisplayUtil.dp2px(mContext,marginEnd);
        switch (gravity){
            case Gravity.TOP:
                lineParams=new LayoutParams(LayoutParams.MATCH_PARENT,strokeWidthPx,Gravity.TOP);
                lineParams.setMargins(marginStart,0,marginEnd,0);
                break;
            case Gravity.BOTTOM:
                lineParams=new LayoutParams(LayoutParams.MATCH_PARENT,strokeWidthPx,Gravity.BOTTOM);
                lineParams.setMargins(marginStart,0,marginEnd,0);
                break;
            case Gravity.LEFT:
                lineParams=new LayoutParams(strokeWidthPx,LayoutParams.MATCH_PARENT,Gravity.LEFT);
                lineParams.setMargins(0,marginStart,0,marginEnd);
                break;
            case Gravity.RIGHT:
                lineParams=new LayoutParams(strokeWidthPx,LayoutParams.MATCH_PARENT,Gravity.RIGHT);
                lineParams.setMargins(0,marginStart,0,marginEnd);
                break;
        }
        if (lineParams==null){
            return;
        }
        line.setBackgroundColor(color);
        addView(line,index,lineParams);
        this.line=index;
        index++;
    }

    /**
     * left text
     * @param text text
     */
    public void setTextLeft(CharSequence text){
        ((TextView)this.getChildAt(leftTextView)).setText(text);
    }

    /**
     * right text
     * @param text text
     */
    public void setTextRight(CharSequence text){
        ((TextView)this.getChildAt(rightTextView)).setText(text);
    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.HorizontalItemView);
        int leftImageSrcId = ta.getResourceId(R.styleable.HorizontalItemView_leftImage_src, 0);
        int rightImageSrcId = ta.getResourceId(R.styleable.HorizontalItemView_rightImage_src, 0);

        String leftText_txt = ta.getString(R.styleable.HorizontalItemView_leftText_text);
        String rightText_txt = ta.getString(R.styleable.HorizontalItemView_rightText_text);

        float leftImage_marginLeft = ta.getDimension(R.styleable.HorizontalItemView_leftImage_marginLeft, 12);
        float rightImage_marginRight = ta.getDimension(R.styleable.HorizontalItemView_rightImage_marginRight,
                12);

        int leftTextColor = ta.getColor(R.styleable.HorizontalItemView_leftText_textColor, Color.BLACK);
        int rightTextColor = ta.getColor(R.styleable.HorizontalItemView_rightText_textColor, Color.BLACK);


        float leftTextSize = ta.getDimension(R.styleable.HorizontalItemView_leftText_textSize, 14);
        float rightTextSize = ta.getDimension(R.styleable.HorizontalItemView_rightText_textSize, 14);


        int gravity = ta.getInt(R.styleable.HorizontalItemView_line_gravity, Gravity.BOTTOM);
        float line_marginStart = ta.getDimension(R.styleable.HorizontalItemView_line_marginStart, 0);
        float line_marginEnd = ta.getDimension(R.styleable.HorizontalItemView_line_marginEnd, 0);
        int line_color = ta.getColor(R.styleable.HorizontalItemView_line_color, Color.GRAY);
        int linePx = ta.getInt(R.styleable.HorizontalItemView_line_strokeWidthPx, 1);

        float leftImage_width = ta.getDimension(R.styleable.HorizontalItemView_leftImage_width, 0);
        float rightImage_width = ta.getDimension(R.styleable.HorizontalItemView_rightImage_width, 0);

        float leftImage_height = ta.getDimension(R.styleable.HorizontalItemView_leftImage_height, 0);
        float rightImage_height = ta.getDimension(R.styleable.HorizontalItemView_rightImage_height, 0);


        float leftText_marginLeft = ta.getDimension(R.styleable.HorizontalItemView_leftText_marginLeft, 0);

        float rightText_marginRight = ta.getDimension(R.styleable.HorizontalItemView_rightText_marginRight, 0);

        ta.recycle();

        //left imageview
        setLeftImageView((int)leftImage_width,(int)leftImage_height,leftImageSrcId,(int)leftImage_marginLeft);
        //right imageview
        setRightImageView((int)rightImage_width,(int)rightImage_height,rightImageSrcId,(int)rightImage_marginRight);

        //left textview
        setLeftTextView(leftText_txt,leftTextColor, DisplayUtil.px2sp(mContext,leftTextSize),(int)leftText_marginLeft);
        //right textview
        setRightTextView(rightText_txt,rightTextColor,DisplayUtil.px2sp(mContext,rightTextSize),(int)rightText_marginRight);

        //line
        setLine(gravity,line_color,linePx,(int)line_marginStart,(int)line_marginEnd);
    }
}
