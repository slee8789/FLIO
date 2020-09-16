package com.fund.flio.ui.main.mypage;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentMyPageBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;


public class MyPageFragment extends BaseFragment<FragmentMyPageBinding, MyPageViewModel>  {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_page;
    }

    @Override
    public MyPageViewModel getViewModel() {
        return getViewModelProvider().get(MyPageViewModel.class);
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
        getViewDataBinding().logout.setOnClickListener(v -> {
            ((MainActivity)getBaseActivity()).getAuthViewModel().logout();
        });
    }


}
