package com.example.kimichael.yamblz_forecast.domain.interactor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Kim Michael on 16.07.17
 */
public abstract class Interactor<R, P> {
    private final Scheduler jobScheduler;
    private final CompositeDisposable subscriptions;
    private final Scheduler uiScheduler;


    public Interactor(Scheduler threadExecutor, Scheduler postExecutionThread) {
        this.jobScheduler = threadExecutor;
        this.uiScheduler = postExecutionThread;
        this.subscriptions = new CompositeDisposable();
    }


    protected abstract Observable<R> buildUseCaseObservable(P params);

    public void execute(DisposableObserver<R> observer, P params) {
        final Observable<R> observable = this.buildUseCaseObservable(params)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler);
        addSubscription(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addSubscription(Disposable disposable) {
        subscriptions.add(disposable);
    }
}