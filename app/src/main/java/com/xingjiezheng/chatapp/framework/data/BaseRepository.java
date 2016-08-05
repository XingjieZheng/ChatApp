package com.xingjiezheng.chatapp.framework.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingjieZheng
 * on 2016/8/5.
 */
public abstract class BaseRepository<T> implements IDataSource<T> {

    private List<RepositoryObserver> observers = new ArrayList<>();
    private boolean cacheIsDirty;
    private IDataSource<T> localDataSource;
    private IDataSource<T> remoteDataSource;

    public void addDataObserver(RepositoryObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeDataObserver(RepositoryObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    private void notifyDataObserver() {
        for (RepositoryObserver observer : observers) {
            observer.onDataChanged();
        }
    }

    public interface RepositoryObserver {
        void onDataChanged();
    }





}
