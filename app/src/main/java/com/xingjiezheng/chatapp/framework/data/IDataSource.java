package com.xingjiezheng.chatapp.framework.data;

/**
 * Created by XingjieZheng
 * on 2016/8/5.
 */
public interface IDataSource<T> {

    T getData();

    void setData();

    void updateData();

    void clearData();
}
