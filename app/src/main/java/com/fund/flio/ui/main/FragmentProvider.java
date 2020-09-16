package com.fund.flio.ui.main;

import com.fund.flio.ui.main.message.MessageFragment;
import com.fund.flio.ui.main.message.alarm.AlarmFragment;
import com.fund.flio.ui.main.community.certificate.CertificateFragment;
import com.fund.flio.ui.main.message.chat.detail.ChatDetailFragment;
import com.fund.flio.ui.main.message.chat.list.ChatListFragment;
import com.fund.flio.ui.main.message.chat.profile.ChatProfileFragment;
import com.fund.flio.ui.main.community.news.NewsFragment;
import com.fund.flio.ui.main.community.event.EventFragment;
import com.fund.flio.ui.main.home.HomeFragment;
import com.fund.flio.ui.main.intro.IntroFragment;
import com.fund.flio.ui.main.login.LoginFragment;
import com.fund.flio.ui.main.market.MarketFragment;
import com.fund.flio.ui.main.mypage.MyPageFragment;
import com.fund.flio.ui.main.community.CommunityFragment;
import com.fund.flio.ui.main.mypage.selled.SelledFragment;
import com.fund.flio.ui.main.mypage.selling.SellingFragment;
import com.fund.flio.ui.main.mypage.selllist.SellListFragment;
import com.fund.flio.ui.main.market.product.ProductFragment;
import com.fund.flio.ui.main.search.SearchFragment;

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
    abstract ChatListFragment provideChatList();

    @ContributesAndroidInjector
    abstract ChatDetailFragment provideChatDetail();

    @ContributesAndroidInjector
    abstract ChatProfileFragment provideChatProfile();

    @ContributesAndroidInjector
    abstract CommunityFragment provideCommunity();

    @ContributesAndroidInjector
    abstract MarketFragment provideMarket();

    @ContributesAndroidInjector
    abstract AlarmFragment provideAlarm();

    @ContributesAndroidInjector
    abstract MyPageFragment provideMore();

    @ContributesAndroidInjector
    abstract EventFragment provideEvent();

    @ContributesAndroidInjector
    abstract CertificateFragment provideCertificate();

    @ContributesAndroidInjector
    abstract NewsFragment provideNews();

    @ContributesAndroidInjector
    abstract ProductFragment provideProduct();

    @ContributesAndroidInjector
    abstract MessageFragment provideMessage();

    @ContributesAndroidInjector
    abstract SellListFragment provideSellList();

    @ContributesAndroidInjector
    abstract SellingFragment provideSelling();

    @ContributesAndroidInjector
    abstract SelledFragment provideSelled();

    @ContributesAndroidInjector
    abstract SearchFragment provideSearch();


}
