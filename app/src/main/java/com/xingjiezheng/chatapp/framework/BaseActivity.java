package com.xingjiezheng.chatapp.framework;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xingjiezheng.chatapp.util.SnackbarUtils;

/**
 * Created by xj
 * on 2016/5/16.
 */
public class BaseActivity extends AppCompatActivity {

    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getRootView();
    }

    protected void showToast(String message) {
        if (rootView == null) {
            return;
        }
        SnackbarUtils.show(rootView, message);
    }

    protected View getRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }
}
