package com.fund.flio.ui.main;

import com.fund.flio.ui.main.category.CategoryFragment;
import com.fund.flio.ui.main.community.certificate.detail.CertificateDetailFragment;
import com.fund.flio.ui.main.community.certificate.detail.reply.CertificateReplyFragment;
import com.fund.flio.ui.main.community.certificate.register.CertificateRegisterFragment;
import com.fund.flio.ui.main.community.event.detail.EventDetailFragment;
import com.fund.flio.ui.main.community.event.detail.reply.EventReplyFragment;
import com.fund.flio.ui.main.community.event.register.EventRegisterFragment;
import com.fund.flio.ui.main.market.product.detail.ProductDetailDialog;
import com.fund.flio.ui.main.market.product.fullscreen.ProductImageFullScreenFragment;
import com.fund.flio.ui.main.market.register.ProductRegisterFragment;
import com.fund.flio.ui.main.market.register.category.ProductRegisterCategoryFragment;
import com.fund.flio.ui.main.market.register.detail.ProductRegisterDetailFragment;
import com.fund.flio.ui.main.market.register.purpose.ProductRegisterPurposeFragment;
import com.fund.flio.ui.main.market.register.tag.ProductRegisterTagFragment;
import com.fund.flio.ui.main.message.MessageFragment;
import com.fund.flio.ui.main.message.reply.list.ReplyListFragment;
import com.fund.flio.ui.main.community.certificate.list.CertificateListFragment;
import com.fund.flio.ui.main.message.chat.detail.ChatDetailFragment;
import com.fund.flio.ui.main.message.chat.list.ChatListFragment;
import com.fund.flio.ui.main.community.news.NewsFragment;
import com.fund.flio.ui.main.community.event.list.EventListFragment;
import com.fund.flio.ui.main.home.HomeFragment;
import com.fund.flio.ui.main.intro.IntroFragment;
import com.fund.flio.ui.main.login.LoginFragment;
import com.fund.flio.ui.main.market.MarketFragment;
import com.fund.flio.ui.main.mypage.MyPageFragment;
import com.fund.flio.ui.main.community.CommunityFragment;
import com.fund.flio.ui.main.mypage.buy.BuyListFragment;
import com.fund.flio.ui.main.mypage.buy.review.ReviewWriteDialog;
import com.fund.flio.ui.main.mypage.favorite.FavoriteListFragment;
import com.fund.flio.ui.main.mypage.favorite.certificate.FavoriteCertificateFragment;
import com.fund.flio.ui.main.mypage.favorite.event.FavoriteEventFragment;
import com.fund.flio.ui.main.mypage.favorite.market.FavoriteProductFragment;
import com.fund.flio.ui.main.mypage.sell.selled.SelledFragment;
import com.fund.flio.ui.main.mypage.sell.selled.review.ReviewDialog;
import com.fund.flio.ui.main.mypage.sell.selling.SellingFragment;
import com.fund.flio.ui.main.mypage.sell.SellListFragment;
import com.fund.flio.ui.main.market.product.ProductFragment;
import com.fund.flio.ui.main.mypage.sell.selling.buyer.SellCompleteBottomSheetDialog;
import com.fund.flio.ui.main.mypage.sell.selling.buyer.guide.BuyerGuideFragment;
import com.fund.flio.ui.main.mypage.sell.selling.buyer.list.BuyerListFragment;
import com.fund.flio.ui.main.mypage.setting.SettingFragment;
import com.fund.flio.ui.main.mypage.setting.logout.DeleteCacheDialog;
import com.fund.flio.ui.main.mypage.setting.logout.LogoutDialog;
import com.fund.flio.ui.main.profile.ProfileFragment;
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
    abstract CommunityFragment provideCommunity();

    @ContributesAndroidInjector
    abstract MarketFragment provideMarket();

    @ContributesAndroidInjector
    abstract ReplyListFragment provideAlarm();

    @ContributesAndroidInjector
    abstract MyPageFragment provideMore();

    @ContributesAndroidInjector
    abstract EventListFragment provideEventList();

    @ContributesAndroidInjector
    abstract EventDetailFragment provideEventDetail();

    @ContributesAndroidInjector
    abstract CertificateListFragment provideCertificateList();

    @ContributesAndroidInjector
    abstract CertificateDetailFragment provideCertificateDetail();

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

    @ContributesAndroidInjector
    abstract SettingFragment provideSetting();

    @ContributesAndroidInjector
    abstract WriteBottomSheetDialog provideWriteBottomSheet();

    @ContributesAndroidInjector
    abstract LogoutDialog provideLogout();

    @ContributesAndroidInjector
    abstract ProductRegisterFragment provideProductRegister();

    @ContributesAndroidInjector
    abstract EventRegisterFragment provideEventRegister();

    @ContributesAndroidInjector
    abstract CertificateRegisterFragment provideCertificateRegister();

    @ContributesAndroidInjector
    abstract CertificateReplyFragment provideCertificateReply();

    @ContributesAndroidInjector
    abstract EventReplyFragment provideEventReply();

    @ContributesAndroidInjector
    abstract ProfileFragment provideProfile();

    @ContributesAndroidInjector
    abstract ProductRegisterDetailFragment provideProductRegisterDetail();

    @ContributesAndroidInjector
    abstract ProductRegisterCategoryFragment provideProductRegisterCategory();

    @ContributesAndroidInjector
    abstract ProductDetailDialog provideProductDetail();

    @ContributesAndroidInjector
    abstract ProductRegisterPurposeFragment provideProductRegisterPurpose();

    @ContributesAndroidInjector
    abstract ProductRegisterTagFragment provideProductRegisterTag();

    @ContributesAndroidInjector
    abstract SellCompleteBottomSheetDialog provideSellComplete();

    @ContributesAndroidInjector
    abstract BuyerGuideFragment provideBuyerGuide();

    @ContributesAndroidInjector
    abstract BuyerListFragment provideBuyerList();

    @ContributesAndroidInjector
    abstract BuyListFragment provideBuyList();

    @ContributesAndroidInjector
    abstract ReviewDialog provideReview();

    @ContributesAndroidInjector
    abstract ReviewWriteDialog provideReviewWrite();

    @ContributesAndroidInjector
    abstract FavoriteListFragment provideFavoriteList();

    @ContributesAndroidInjector
    abstract FavoriteProductFragment provideFavoriteProduct();

    @ContributesAndroidInjector
    abstract FavoriteEventFragment provideFavoriteEvent();

    @ContributesAndroidInjector
    abstract FavoriteCertificateFragment provideFavoriteCertificate();

    @ContributesAndroidInjector
    abstract CategoryFragment provideCategory();

    @ContributesAndroidInjector
    abstract DeleteCacheDialog provideDeleteCache();

    @ContributesAndroidInjector
    abstract ProductImageFullScreenFragment provideProductImageFullScreen();



}
