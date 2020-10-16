package com.fund.flio.ui.main.market.product.fullscreen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import androidx.viewpager2.widget.ViewPager2;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentProductBinding;
import com.fund.flio.databinding.FragmentProductImageBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.home.ProductSmallAdapter;
import com.fund.flio.ui.main.market.product.ProductFragmentArgs;
import com.fund.flio.ui.main.market.product.ProductImageAdapter;
import com.fund.flio.ui.main.mypage.buy.review.ReviewWriteDialogArgs;
import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;


public class ProductImageFullScreenFragment extends BaseFragment<FragmentProductImageBinding, ProductImageFullScreenViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_image;
    }

    @Override
    public ProductImageFullScreenViewModel getViewModel() {
        return getViewModelProvider().get(ProductImageFullScreenViewModel.class);
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
        getViewModel().image.set(ProductImageFullScreenFragmentArgs.fromBundle(getArguments()).getImageUrl());
//        getViewDataBinding().image.setImageURI(Uri.parse(ProductImageFullScreenFragmentArgs.fromBundle(getArguments()).getImageUrl()));
//        getViewDataBinding().image.setImageBitmap(getImageBitmap(ProductImageFullScreenFragmentArgs.fromBundle(getArguments()).getImageUrl()));
    }

//    private Bitmap getImageBitmap(String url) {
//        Bitmap bm = null;
//        try {
//            URL aURL = new URL(url);
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();
//        } catch (IOException e) {
//        }
//        return bm;
//    }

}
