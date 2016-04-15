package com.xingjiezheng.chatapp.framework;

/**
 * Created by XingjieZheng
 * on 2016/4/15.
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {
    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public Q getmRequestValues() {
        return mRequestValues;
    }

    public void setmRequestValues(Q mRequestValues) {
        this.mRequestValues = mRequestValues;
    }

    public UseCaseCallback<P> getmUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setmUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.mUseCaseCallback = mUseCaseCallback;
    }

    public static class RequestValues {
    }

    public static class ResponseValues {
    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);

        void onError(Error error);
    }


}
