package com.example.czp.cookbook.api;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class RxManager {

    private CompositeDisposable disposable = new CompositeDisposable();

    public void register(Disposable d) {
        disposable.add(d);
    }

    public void unSubscribe() {
        disposable.dispose();
    }

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
