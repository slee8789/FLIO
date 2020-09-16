package com.fund.flio.ui.main.message.chat.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentChatProfileBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;

import javax.inject.Inject;


public class ChatProfileFragment extends BaseFragment<FragmentChatProfileBinding, ChatProfileViewModel>  {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_detail;
    }

    @Override
    public ChatProfileViewModel getViewModel() {
        return new ViewModelProvider(getViewModelStore(), viewModelProviderFactory).get(ChatProfileViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {

    }

}
