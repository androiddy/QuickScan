package com.duyin.quickscan.api.RxUtlis;


import rx.Subscriber;

/**
 * Created by zhangzhongping on 17/1/11.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        _onError("获取信息错误!");
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}