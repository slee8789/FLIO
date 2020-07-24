package com.fund.flio.di.provider;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler newwThread();

    Scheduler computation();

}
