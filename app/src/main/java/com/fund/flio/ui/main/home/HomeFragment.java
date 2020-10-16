package com.fund.flio.ui.main.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.TransitionInflater;
import androidx.viewpager2.widget.ViewPager2;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Certificate;
import com.fund.flio.data.model.Event;
import com.fund.flio.data.model.Product;

import com.fund.flio.databinding.FragmentHomeBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.community.event.list.EventAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;
import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    @Inject
    DataManager dataManager;

    @Inject
    BannerImageAdapter mBannerImageAdapter;

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    ProductSmallAdapter mProductSmallAdapter;

    @Inject
    CertificateSmallAdapter mCertificateSmallAdapter;

    private int imagePosition;
    private Disposable imageSlideDisposable;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return getViewModelProvider().get(HomeViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().setMainViewModel(getMainViewModel());
        getViewModel().mainProduct();
        initViews();
        setupActionBar();
        getViewDataBinding().headerProducts.setOnClickListener(v -> {
//            Logger.d("Firebase Auth " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        });
        postponeEnterTransition();
        getViewDataBinding().products.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });
    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar.toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getViewDataBinding().toolbar.search.setOnClickListener(v -> Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_search));
        getViewDataBinding().toolbar.favorite.setOnClickListener(v -> Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_favorite_list));
        getViewDataBinding().toolbar.menu.setOnClickListener(v -> Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_category));
    }


    private void initViews() {
        mProductSmallAdapter.setMainViewModel(getMainViewModel());
        getViewDataBinding().products.setAdapter(mProductSmallAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_horizontal)));
        getViewDataBinding().products.addItemDecoration(dividerItemDecoration);
        getViewDataBinding().certificates.setAdapter(mCertificateSmallAdapter);
        getViewDataBinding().certificates.addItemDecoration(dividerItemDecoration);
        ArrayList<Certificate> certificates = new Gson().fromJson(readAssetJson(getContext(), "certificates.json"), new TypeToken<List<Certificate>>() {
        }.getType());
        mCertificateSmallAdapter.addItems(certificates);

        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductSmallAdapter.setItems(products));

        getViewDataBinding().banner.setAdapter(mBannerImageAdapter);
        ArrayList<Banner> banners = new Gson().fromJson(readAssetJson(getContext(), "banners.json"), new TypeToken<List<Banner>>() {
        }.getType());
        mBannerImageAdapter.addItems(banners);
        imageSlideDisposable = Observable.interval(8, TimeUnit.SECONDS)
                .observeOn(schedulerProvider.ui())
                .subscribe(time -> {
                    Logger.d("banner index ");
                    if (imagePosition == mBannerImageAdapter.getItemCount() - 1) {
                        getViewDataBinding().banner.setCurrentItem(0, true);
                    } else {
                        getViewDataBinding().banner.setCurrentItem(imagePosition + 1, true);
                    }
//                    addBottomDots(imagePosition);
                });
        getViewDataBinding().banner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                Logger.d("onPageSelected " + position);
                imagePosition = position;
            }


            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Logger.d("onPageScrollStateChanged " + state);
                switch (state) {
                    case SCROLL_STATE_IDLE:
                        break;

                    case SCROLL_STATE_DRAGGING:
                        break;

                    case SCROLL_STATE_SETTLING:
                        break;
                }
            }
        });
    }
}
