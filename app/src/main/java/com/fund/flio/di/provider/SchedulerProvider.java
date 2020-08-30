package com.fund.flio.di.provider;


import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler newThread();

    Scheduler computation();

}
