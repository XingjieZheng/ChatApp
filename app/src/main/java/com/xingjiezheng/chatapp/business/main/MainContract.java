package com.xingjiezheng.chatapp.business.main;

import android.app.Activity;
import android.content.Context;

import com.xingjiezheng.chatapp.framework.BasePresenter;
import com.xingjiezheng.chatapp.framework.BaseView;

/**
 * Created by XingjieZheng
 * on 2016/7/7.
 */
public interface MainContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void gotoActivityAndFinishMyself(Class<? extends Activity> activityClass);

    }

    interface Presenter extends BasePresenter {

    }
}
