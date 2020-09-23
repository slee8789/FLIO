package com.fund.flio.di.provider;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AppSchedulerProvider implements SchedulerProvider {

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    @Override
    public io.reactivex.Scheduler ui2() {
        return io.reactivex.android.schedulers.AndroidSchedulers.mainThread();
    }

    @Override
    public io.reactivex.Scheduler io2() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    @Override
    public io.reactivex.Scheduler newThread2() {
        return io.reactivex.schedulers.Schedulers.newThread();
    }

    @Override
    public io.reactivex.Scheduler computation2() {
        return io.reactivex.schedulers.Schedulers.computation();
    }
}
