package com.fund.flio.di.builder;

import com.fund.flio.ui.main.FragmentProvider;
import com.fund.flio.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = {
            FragmentProvider.class
    })
    abstract MainActivity bindMainActivity();

}
