package com.example.kimichael.yamblz_forecast.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Kim Michael on 16.07.17
 */
public abstract class SingleInteractor<R, P> {
    private final Scheduler jobScheduler;
    private final Scheduler uiScheduler;


    public SingleInteractor(Scheduler threadExecutor, Scheduler postExecutionThread) {
        this.jobScheduler = threadExecutor;
        this.uiScheduler = postExecutionThread;
    }


    protected abstract Single<R> buildUseCaseObservable(P params);

    public void execute(SingleObserver<R> observer, P params) {
        final Single<R> observable = this.buildUseCaseObservable(params)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler);
        observable.subscribeWith(observer);
    }

}