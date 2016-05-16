package com.xingjiezheng.chatapp.usecase;

/**
 * Created by XingjieZheng
 * on 2016/4/15.
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {
    private Q requestValues;

    private UseCaseCallback<P> useCaseCallback;

    void run() {
        executeUseCase(requestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public Q getRequestValue() {
        return requestValues;
    }

    public void setRequestValues(Q requestValues) {
        this.requestValues = requestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return useCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.useCaseCallback = mUseCaseCallback;
    }

    public static class RequestValues {
    }

    public static class ResponseValue {
    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);

        void onError(Error error);
    }


}
