package com.fund.flio.ui.main;

import com.fund.flio.ui.main.chat.detail.ChatDetailFragment;
import com.fund.flio.ui.main.chat.list.ChatListFragment;
import com.fund.flio.ui.main.chat.profile.ChatProfileFragment;
import com.fund.flio.ui.main.detail.DetailFragment;
import com.fund.flio.ui.main.home.HomeFragment;
import com.fund.flio.ui.main.intro.IntroFragment;
import com.fund.flio.ui.main.login.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentProvider {

    @ContributesAndroidInjector
    abstract IntroFragment provideIntro();

    @ContributesAndroidInjector
    abstract LoginFragment provideLogin();

    @ContributesAndroidInjector
    abstract HomeFragment provideHome();

    @ContributesAndroidInjector
    abstract DetailFragment provideDetail();

    @ContributesAndroidInjector
    abstract ChatListFragment provideChatList();

    @ContributesAndroidInjector
    abstract ChatDetailFragment provideChatDetail();

    @ContributesAndroidInjector
    abstract ChatProfileFragment provideChatProfile();


}
