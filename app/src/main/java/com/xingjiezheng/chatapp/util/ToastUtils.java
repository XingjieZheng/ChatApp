package com.xingjiezheng.chatapp.util;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Utilities for displaying toast notifications
 */
public class ToastUtils {

    /**
     * Show the given message in a {@link Toast}
     * <p/>
     * This method may be called from any thread
     *
     * @param activity
     * @param message
     */
    public static void show(final Activity activity, final String message) {
        final Application context = activity.getApplication();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Show the message with the given resource id in a {@link Toast}
     * <p/>
     * This method may be called from any thread
     *
     * @param activity
     * @param resId
     */
    public static void show(final Activity activity, final int resId) {
        if (activity == null)
            return;

        show(activity, activity.getString(resId));
    }

    /**
     * Show {@link Toast} for throwable
     * <p/>
     * This given default message will be used if an message can not be derived
     * from the given {@link Exception}
     * <p/>
     * This method may be called from any thread
     *
     * @param activity
     * @param e
     * @param defaultMessage
     */
    public static void show(final Activity activity, final Throwable e,
                            final int defaultMessage) {
        if (activity == null)
            return;

        String message = Log.getStackTraceString(e);
        if (TextUtils.isEmpty(message))
            message = activity.getString(defaultMessage);

        show(activity, message);
    }
}
