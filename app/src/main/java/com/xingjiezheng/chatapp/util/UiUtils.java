package com.xingjiezheng.chatapp.util;

import android.content.Context;

/**
 * Created by XingjieZheng
 * on 2016/4/4.
 */
public class UiUtils {

    private UiUtils() {

    }

    public static int dip2px(Context context, float dip) {
        if (context != null && context.getResources() != null && context.getResources().getDisplayMetrics() != null) {
            return (int) (context.getResources().getDisplayMetrics().density * dip + 0.5f);
        } else {
            return 0;
        }
    }
}
