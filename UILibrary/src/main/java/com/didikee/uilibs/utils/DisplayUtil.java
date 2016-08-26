package com.didikee.uilibs.utils;

import android.content.Context;

/**
 * Created by didik on 2016/7/30.
 * 关于dp px sp 转换
 */
public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param context context
     *            （DisplayMetrics类中属性density）
     * @return float cast to int
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context context
     *            （DisplayMetrics类中属性density）
     * @return float cast to int
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px单位转换为sp
     *
     * @param pxValue need px
     * @param context context
     *         DisplayMetrics类中属性scaledDensity
     * @return float cast to int
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue need sp
     * @param context context
     *         DisplayMetrics类中属性scaledDensity
     * @return float cast to int
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
