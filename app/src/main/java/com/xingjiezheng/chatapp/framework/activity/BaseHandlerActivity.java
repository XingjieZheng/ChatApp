package com.xingjiezheng.chatapp.framework.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xingjiezheng.chatapp.util.SnackbarUtils;

/**
 * Created by xj
 * on 2016/5/16.
 */
public abstract class BaseHandlerActivity extends BaseActivity implements Handler.Callback {

    protected Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        handler = new Handler(this);
    }

}
