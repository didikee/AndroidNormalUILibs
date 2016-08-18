package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.didikee.uilibs.utils.DisplayUtil;

/**
 * Created by didik on 2016/8/18.
 *
 * new HorizontalItemView(context) only!!!
 * create HorizontalItemView in java around!
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
        init(context);
    }
    public void init(Context context){
        this.mContext=context;
    }

    /**
     * left imageview
     * @param width  -1:match_parent -2:wrap_content
     * @param height -1:match_parent -2:wrap_content
     * @param drawableId res id
     */
    public void setLeftImageView(int width,int height,int drawableId,int marginLeft){
        //添加左边的ImageView
        ImageView leftImg=new ImageView(mContext);
        LayoutParams leftImageParams=new LayoutParams(DisplayUtil.dp2px(mContext,width),DisplayUtil.dp2px(mContext,height), Gravity.LEFT|Gravity.CENTER_VERTICAL);
        leftImg.setImageResource(drawableId);
        leftImageParams.setMargins(DisplayUtil.dp2px(mContext,marginLeft),0,0,0);
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
        LayoutParams righImageParams=new LayoutParams(DisplayUtil.dp2px(mContext,width),DisplayUtil.dp2px(mContext,height), Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        rightImg.setImageResource(drawableId);
        righImageParams.setMargins(0,0,DisplayUtil.dp2px(mContext,marginRight),0);
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
        leftTvParams.setMargins(DisplayUtil.dp2px(mContext,marginLeft),0,0,0);
        addView(leftTv,index,leftTvParams);
        leftTextView=index;
        index++;
    }

    /**
     * right textview
     * @param txt text content
     * @param textColor color
     * @param textSize textSize
     * @param marginRight margin left to parent
     */
    public void setRightTextView(String txt,int textColor,float textSize,int marginRight){
        //添加左边的Textview
        TextView rightTv=new TextView(mContext);
        LayoutParams rightTvParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        rightTv.setText(TextUtils.isEmpty(txt)?"":txt);
        rightTv.setTextColor(textColor);
        rightTv.setTextSize(textSize<=0?12:textSize);
        rightTvParams.setMargins(0,0,DisplayUtil.dp2px(mContext,marginRight),0);
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
        marginStart=DisplayUtil.dp2px(mContext,marginStart);
        marginEnd=DisplayUtil.dp2px(mContext,marginEnd);
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

}
