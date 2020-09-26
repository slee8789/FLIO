package com.fund.flio.ui.main.market.register;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.databinding.FragmentProductRegisterBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ProductRegisterFragment extends BaseFragment<FragmentProductRegisterBinding, ProductRegisterViewModel> implements OnFABMenuSelectedListener {

    @Inject
    ThumbnailAdapter mThumbnailAdapter;

    private MenuItem menuCompleted;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_register;
    }

    @Override
    public ProductRegisterViewModel getViewModel() {
        return getViewModelProvider().get(ProductRegisterViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setupActionBar();
    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_done, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menuCompleted = menu.findItem(R.id.menu_done);
        //Todo : test
//        menuCompleted.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;

            case R.id.menu_done:
                getViewModel().registerProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViews() {
        getViewDataBinding().thumbnails.setAdapter(mThumbnailAdapter);
        mThumbnailAdapter.setProductRegisterViewModel(getViewModel());
        getViewModel().getThumbnailUris().observe(getViewLifecycleOwner(), thumbnailObserver);
    }

    private final Observer<List<Uri>> thumbnailObserver = thumbnails -> mThumbnailAdapter.setItems(thumbnails);

    @Override
    public void onMenuItemSelected(View view, int id) {

    }
}
