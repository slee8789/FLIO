package com.fund.flio.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.main.MainViewModel;
import com.fund.flio.ui.main.chat.detail.ChatDetailViewModel;
import com.fund.flio.ui.main.chat.list.ChatListViewModel;
import com.fund.flio.ui.main.chat.profile.ChatProfileViewModel;
import com.fund.flio.ui.main.detail.DetailViewModel;
import com.fund.flio.ui.main.home.HomeViewModel;
import com.fund.flio.ui.main.intro.IntroViewModel;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context context;
    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;
    private final ResourceProvider resourceProvider;
    private final FirebaseAuth firebaseAuth;

    @Inject
    public ViewModelProviderFactory(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider, FirebaseAuth firebaseAuth) {
        this.context = context;
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.resourceProvider = resourceProvider;
        this.firebaseAuth = firebaseAuth;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        if (MainViewModel.class.isAssignableFrom(modelClass)) {
            return new MainViewModel(dataManager, schedulerProvider, resourceProvider, firebaseAuth);
        } else if (IntroViewModel.class.isAssignableFrom(modelClass)) {
            return new IntroViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (LoginViewModel.class.isAssignableFrom(modelClass)) {
            return new LoginViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (HomeViewModel.class.isAssignableFrom(modelClass)) {
            return new HomeViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (DetailViewModel.class.isAssignableFrom(modelClass)) {
            return new DetailViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ChatListViewModel.class.isAssignableFrom(modelClass)) {
            return new ChatListViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ChatDetailViewModel.class.isAssignableFrom(modelClass)) {
            return new ChatDetailViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ChatProfileViewModel.class.isAssignableFrom(modelClass)) {
            return new ChatProfileViewModel(dataManager, schedulerProvider, resourceProvider);
        } else
            throw new IllegalArgumentException("Unknown class name");
    }
}
