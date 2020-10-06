package com.fund.flio.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainViewModel;
import com.fund.flio.ui.main.community.certificate.detail.CertificateDetailViewModel;
import com.fund.flio.ui.main.community.certificate.list.CertificateListViewModel;
import com.fund.flio.ui.main.community.certificate.register.CertificateRegisterViewModel;
import com.fund.flio.ui.main.community.event.detail.EventDetailViewModel;
import com.fund.flio.ui.main.community.event.register.EventRegisterViewModel;
import com.fund.flio.ui.main.market.product.ProductViewModel;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.fund.flio.ui.main.message.MessageViewModel;
import com.fund.flio.ui.main.message.reply.list.ReplyListViewModel;
import com.fund.flio.ui.main.message.chat.detail.ChatDetailViewModel;
import com.fund.flio.ui.main.message.chat.list.ChatListViewModel;
import com.fund.flio.ui.main.community.CommunityViewModel;
import com.fund.flio.ui.main.community.event.list.EventViewModel;
import com.fund.flio.ui.main.community.news.NewsViewModel;
import com.fund.flio.ui.main.home.HomeViewModel;
import com.fund.flio.ui.main.intro.IntroViewModel;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.fund.flio.ui.main.market.MarketViewModel;
import com.fund.flio.ui.main.mypage.MyPageViewModel;
import com.fund.flio.ui.main.mypage.sell.SellListViewModel;
import com.fund.flio.ui.main.mypage.setting.SettingViewModel;
import com.fund.flio.ui.main.profile.ProfileViewModel;
import com.fund.flio.ui.main.search.SearchViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.nhn.android.naverlogin.OAuthLogin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context context;
    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;
    private final ResourceProvider resourceProvider;
    private final FirebaseAuth firebaseAuth;
    private final OAuthLogin oAuthLogin;

    @Inject
    public ViewModelProviderFactory(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider, FirebaseAuth firebaseAuth, OAuthLogin oAuthLogin) {
        this.context = context;
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.resourceProvider = resourceProvider;
        this.firebaseAuth = firebaseAuth;
        this.oAuthLogin = oAuthLogin;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        if (MainViewModel.class.isAssignableFrom(modelClass)) {
            return new MainViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (IntroViewModel.class.isAssignableFrom(modelClass)) {
            return new IntroViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (LoginViewModel.class.isAssignableFrom(modelClass)) {
            return new LoginViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (HomeViewModel.class.isAssignableFrom(modelClass)) {
            return new HomeViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ChatListViewModel.class.isAssignableFrom(modelClass)) {
            return new ChatListViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ChatDetailViewModel.class.isAssignableFrom(modelClass)) {
            return new ChatDetailViewModel(context, dataManager, schedulerProvider, resourceProvider);
        } else if (CommunityViewModel.class.isAssignableFrom(modelClass)) {
            return new CommunityViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (NewsViewModel.class.isAssignableFrom(modelClass)) {
            return new NewsViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (MarketViewModel.class.isAssignableFrom(modelClass)) {
            return new MarketViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ReplyListViewModel.class.isAssignableFrom(modelClass)) {
            return new ReplyListViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (MyPageViewModel.class.isAssignableFrom(modelClass)) {
            return new MyPageViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (AuthViewModel.class.isAssignableFrom(modelClass)) {
            return new AuthViewModel(context, dataManager, schedulerProvider, resourceProvider, oAuthLogin);
        } else if (ProductViewModel.class.isAssignableFrom(modelClass)) {
            return new ProductViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (CertificateListViewModel.class.isAssignableFrom(modelClass)) {
            return new CertificateListViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (EventViewModel.class.isAssignableFrom(modelClass)) {
            return new EventViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (MessageViewModel.class.isAssignableFrom(modelClass)) {
            return new MessageViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (SellListViewModel.class.isAssignableFrom(modelClass)) {
            return new SellListViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (SearchViewModel.class.isAssignableFrom(modelClass)) {
            return new SearchViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (SettingViewModel.class.isAssignableFrom(modelClass)) {
            return new SettingViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (EventDetailViewModel.class.isAssignableFrom(modelClass)) {
            return new EventDetailViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (CertificateDetailViewModel.class.isAssignableFrom(modelClass)) {
            return new CertificateDetailViewModel(dataManager, schedulerProvider, resourceProvider);
        } else if (ProductRegisterViewModel.class.isAssignableFrom(modelClass)) {
            return new ProductRegisterViewModel(context, dataManager, schedulerProvider, resourceProvider);
        } else if (EventRegisterViewModel.class.isAssignableFrom(modelClass)) {
            return new EventRegisterViewModel(context, dataManager, schedulerProvider, resourceProvider);
        } else if (CertificateRegisterViewModel.class.isAssignableFrom(modelClass)) {
            return new CertificateRegisterViewModel(context, dataManager, schedulerProvider, resourceProvider);
        } else if (ProfileViewModel.class.isAssignableFrom(modelClass)) {
            return new ProfileViewModel(dataManager, schedulerProvider, resourceProvider);
        } else
            throw new IllegalArgumentException("Unknown class name " + modelClass.getName());
    }
}
