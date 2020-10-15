package com.fund.flio.ui.main.mypage;


import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Keyword;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.logger.Logger;

import org.mozilla.javascript.tools.jsc.Main;

import java.util.List;

public class MyPageViewModel extends BaseViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> rating = new ObservableField<>();
    public ObservableField<String> ratingPoint = new ObservableField<>();
    public ObservableField<String> keywordCount = new ObservableField<>();
    public MutableLiveData<String> keyword = new MutableLiveData<>();
    public MutableLiveData<List<Keyword>> keywords = new MutableLiveData<>();
    private Keyword mKeyword = new Keyword();

    public MutableLiveData<String> getKeyword() {
        return keyword;
    }

    public MutableLiveData<List<Keyword>> getKeywords() {
        return keywords;
    }

    public MyPageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        imageUrl.set(dataManager.getUserImageUrl());
        name.set(dataManager.getUserName());
        rating.set("5");
        ratingPoint.set("(5)");
        getCompositeDisposable2().add(dataManager.getKeywords()
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe(keywords -> {
                    this.keywords.setValue(keywords);
                    keywordCount.set(keywords.size() + "/10");
                }));
    }

    private void showSnackBar(View v, String message) {
        //Todo : margin
        Snackbar snackBar = Snackbar.make(((MainActivity) v.getContext()).getViewDataBinding().getRoot(), message, Snackbar.LENGTH_SHORT);
        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBar.getView().getLayoutParams();
        params.setMargins(12, 12, 12, 12);
        snackBar.getView().setLayoutParams(params);
        snackBar.setBackgroundTint(getResourceProvider().getColor(R.color.purple));
        snackBar.show();
    }

    public void onKeywordRegisterClick(View v) {
        if (keywords.getValue().size() == 10) {
            showSnackBar(v, getResourceProvider().getString(R.string.message_max_count));
            return;
        }
        mKeyword.setKeyword(keyword.getValue());
        getCompositeDisposable2().add(getDataManager().insertKeyword(mKeyword)
                .doOnNext(result -> Logger.i("insert result " + result))
                .concatMap(result -> getDataManager().getKeywords())
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe(result -> {
                    keywords.setValue(result);
                    keywordCount.set(keywords.getValue().size() + "/10");
                }, onError -> showSnackBar(v, getResourceProvider().getString(R.string.message_already_registered))));

        keyword.setValue("");
    }

    public void onKeywordDelete(Keyword keyword) {
        getCompositeDisposable2().add(getDataManager().deleteKeyword(keyword)
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe(result -> keywordCount.set(keywords.getValue().size() + "/10")));
    }

    public void onProductSellClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_my_page_to_nav_sell_list);
    }

    public void onProductBuyClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_my_page_to_nav_buy_list);
    }

    public void onProductFavoriteClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_my_page_to_nav_favorite_list);
    }

    public void onSettingReviewClick(View v) {

    }

    public void onSettingPromotionClick(View v) {

    }

}
