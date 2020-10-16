package com.fund.flio.ui.main.market.product;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentProductBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.home.ProductSmallAdapter;
import com.google.android.material.internal.CollapsingTextHelper;
import com.orhanobut.logger.Logger;


import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;


public class ProductFragment extends BaseFragment<FragmentProductBinding, ProductViewModel> {

    @Inject
    ProductSmallAdapter mProductSmallAdapter;

    @Inject
    ProductImageAdapter mProductImageAdapter;

    @Inject
    SchedulerProvider schedulerProvider;

    private TextView[] dots;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        return getViewModelProvider().get(ProductViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move));
        setHasOptionsMenu(true);
//        getBaseActivity().hideSystemUI();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postponeEnterTransition();
        getViewDataBinding().flio.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });
        getViewDataBinding().faith.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });
        initViews();
        setupActionBar();


    }

    private int imagePosition;
    private Disposable imageSlideDisposable;

    private void initViews() {
        getViewModel().setProductFragment(this);
        getViewDataBinding().recommends.setAdapter(mProductSmallAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_horizontal)));
        getViewDataBinding().recommends.addItemDecoration(dividerItemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductSmallAdapter.setItems(products));

        getViewDataBinding().image.setAdapter(mProductImageAdapter);
        mProductImageAdapter.setProductViewModel(getViewModel());
        imageSlideDisposable = Observable.interval(5, TimeUnit.SECONDS)
                .observeOn(schedulerProvider.ui())
                .subscribe(time -> {
                    if (imagePosition == mProductImageAdapter.getItemCount() - 1) {
                        getViewDataBinding().image.setCurrentItem(0, true);
                    } else {
                        getViewDataBinding().image.setCurrentItem(imagePosition + 1, true);
                    }
//                    addBottomDots(imagePosition);
                });
        getViewDataBinding().image.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                Logger.d("onPageSelected " + position);
                imagePosition = position;
                new Handler().postDelayed(() -> {
                    addBottomDots(imagePosition);
                }, 0);
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
        getViewModel().getProductImages().observe(getViewLifecycleOwner(), products -> mProductImageAdapter.setItems(products));

        Logger.d("test 1 " + ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId());
        Logger.d("test 2 " + requireContext().getResources().getString(R.string.transition_product_image, ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId()));
        getViewModel().detailProduct(String.valueOf(ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId()));
        getViewModel().purposeProduct(ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId(), String.valueOf(ProductFragmentArgs.fromBundle(getArguments()).getProduct().getPurpose()));
        getViewDataBinding().image.setTransitionName(requireContext().getResources().getString(R.string.transition_product_image, ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId()));
        getViewDataBinding().flio.setTransitionName(requireContext().getResources().getString(R.string.transition_product_flio, ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId()));
        getViewDataBinding().faith.setTransitionName(requireContext().getResources().getString(R.string.transition_product_faith, ProductFragmentArgs.fromBundle(getArguments()).getProduct().getProductId()));
        Logger.d("test image " + getViewDataBinding().image.getTransitionName());
        Logger.d("test flio " + getViewDataBinding().flio.getTransitionName());
        addBottomDots(0);

    }

    private void addBottomDots(int currentPage) {
        getViewDataBinding().layoutDots.removeAllViews();
        dots = new TextView[ProductFragmentArgs.fromBundle(getArguments()).getProduct().getImageUrl().split(",").length];
        Logger.d("addBottomDots " + dots.length);
        for (int i = 0; i < dots.length; i++) {
            Logger.d("addBottomDots test " + getBaseActivity() + ", " + dots[i]);
            dots[i] = new TextView(getBaseActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(getContext(), R.color.dot_light_screen4));
            getViewDataBinding().layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(ContextCompat.getColor(getContext(), R.color.dot_dark_screen4));

    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getViewDataBinding().toolbar.setPadding(0, getStatusBarHeight(getContext()), 0, 0);
        getBaseActivity().hideSystemUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageSlideDisposable.dispose();
    }
}
