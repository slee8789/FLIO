package com.fund.flio.di.provider;


import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler newThread();

    Scheduler computation();

    io.reactivex.Scheduler ui2();

    io.reactivex.Scheduler io2();

    io.reactivex.Scheduler newThread2();

    io.reactivex.Scheduler computation2();

}
