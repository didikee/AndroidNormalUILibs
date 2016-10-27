package com.didikee.uilibs.viewgroups;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by didik on 2016/10/13.
 *
 * <p>add protected onScrollChanged</p>
 * <p> isAtTop() and isAtBottom() </p>
 * <p> isChildVisible(View child) </p>
 */

public class XScrollView extends ScrollView {
    public XScrollView(Context context) {
        super(context);
    }

    public XScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnScrollChangedListener{
        public void onScrollChanged(int x, int y, int oldX, int oldY);
    }
    private OnScrollChangedListener onScrollChangedListener;
    /**
     *
     * @param onScrollChangedListener
     */
    public void setOnScrollListener(OnScrollChangedListener onScrollChangedListener){
        this.onScrollChangedListener=onScrollChangedListener;
    }
    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY){
        super.onScrollChanged(x, y, oldX, oldY);
        if(onScrollChangedListener!=null){
            onScrollChangedListener.onScrollChanged(x, y, oldX, oldY);
        }
    }

    /******************************* is bottom or top *****************************/

    /**
     *
     * @return
     */
    public boolean isAtTop(){
        return getScrollY()<=0;
    }
    /**
     *
     * @return
     */
    public boolean isAtBottom(){
        return getScrollY()==getChildAt(getChildCount()-1).getBottom()+getPaddingBottom()-getHeight();
    }

    /******************************* isChildVisible ************************************/
    /**
     * 判断view是否在屏幕中可见
     * @param child view
     */
    public boolean isChildVisible(View child){
        if(child==null){
            return false;
        }
        Rect scrollBounds = new Rect();
        getHitRect(scrollBounds);
        return child.getLocalVisibleRect(scrollBounds);
    }
}
