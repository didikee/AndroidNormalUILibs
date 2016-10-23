package com.didikee.uilibs.utils;

import android.graphics.Color;

/**
 * Created by didikee on 2016/10/23.
 */

public class UIColor {

    /**
     * 获取Android Color 并附带 透明度
     * @param alpha 0~255
     * @param color color
     * @return color with alpha
     */
    public static int getAlphaColor(int alpha,int color){
        alpha = alpha < 0 ? 0 : (alpha > 255 ? 255 : alpha);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.argb(alpha, r, g, b);
    }
}
