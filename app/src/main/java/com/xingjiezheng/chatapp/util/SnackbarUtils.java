package com.xingjiezheng.chatapp.util;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by XingjieZheng
 * on 2016/4/4.
 */
public class SnackbarUtils {

    private SnackbarUtils() {

    }

    public static void show(View parent, String message) {
        if (parent != null && !TextUtils.isEmpty(message)) {
            Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
        }
    }

    public static void show(View parent, String message, int backgroundColor) {
        if (parent != null && !TextUtils.isEmpty(message)) {
            Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
            try {
                //modify background
                Field field = Snackbar.class.getDeclaredField("mView");
                field.setAccessible(true);
                Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) field.get(snackbar);
                view.setBackgroundColor(backgroundColor);
                field.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                snackbar.show();
            }
        }
    }

    public static void show(View parent, String message, Drawable leftDrawable) {
        if (parent != null && !TextUtils.isEmpty(message)) {
            Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
            try {
                //getResource.getDrawable() 方法不存在。
                if (leftDrawable != null) {
                    Field field = Snackbar.class.getDeclaredField("mView");
                    field.setAccessible(true);
                    Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) field.get(snackbar);
                    field.setAccessible(false);
                    field = Snackbar.SnackbarLayout.class.getDeclaredField("mMessageView");
                    field.setAccessible(true);
                    TextView textView = (TextView) field.get(view);
                    textView.setCompoundDrawablePadding(UiUtils.dip2px(textView.getContext(), 15));
                    textView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                snackbar.show();
            }
        }
    }

    public static void show(View parent, String message, int backgroundColor, Drawable leftDrawable) {
        if (parent != null && !TextUtils.isEmpty(message)) {
            Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
            try {
                //修改背景
                Field field = Snackbar.class.getDeclaredField("mView");
                field.setAccessible(true);
                Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) field.get(snackbar);
                view.setBackgroundColor(backgroundColor);
                field.setAccessible(false);

                //getResource.getDrawable() 方法不存在。
                if (leftDrawable != null) {
                    field = Snackbar.SnackbarLayout.class.getDeclaredField("mMessageView");
                    field.setAccessible(true);
                    TextView textView = (TextView) field.get(view);
                    textView.setCompoundDrawablePadding(UiUtils.dip2px(textView.getContext(), 15));
                    textView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                snackbar.show();
            }
        }
    }
}
