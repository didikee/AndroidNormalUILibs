package com.didikee.uilibs.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;

/**
 * Created by didik on 2016/10/27.
 * <p>在接口的 onFinish() 方法中调用此方法,让TextView重新可点击</p>
 */

public class WaitFinishTextView extends TextView{

    protected OnClickListener mOnClickListener;

    public WaitFinishTextView(Context context) {
        super(context);
    }

    public WaitFinishTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaitFinishTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (!this.isClickable()){
            this.setClickable(true);
        }
        this.mOnClickListener=l;
    }

    @Override
    public boolean performClick() {
        final boolean result;
        if (mOnClickListener != null) {
            playSoundEffect(SoundEffectConstants.CLICK);
            mOnClickListener.onClick(this);
            this.setClickable(false);
            result = true;
        } else {
            result = false;
        }
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
        return result;
    }

    /**
     * 在接口的 onFinish() 方法中调用此方法,让TextView重新可点击
     */
    public void onFinish(){
        this.setClickable(true);
    }
}
