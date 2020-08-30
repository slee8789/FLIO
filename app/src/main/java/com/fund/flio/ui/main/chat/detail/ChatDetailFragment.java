package com.fund.flio.ui.main.chat.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Message;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentChatDetailBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class ChatDetailFragment extends BaseFragment<FragmentChatDetailBinding, ChatDetailViewModel> {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    ChatAdapter mChatAdapter;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_detail;
    }

    @Override
    public ChatDetailViewModel getViewModel() {
        return getViewModelProvider().get(ChatDetailViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        getViewDataBinding().chats.setAdapter(mChatAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().chats.addItemDecoration(dividerItemDecoration);
        ArrayList<Message> testRecommends = new ArrayList<>();
        testRecommends.add(new Message("", "H", MessageType.LOCAL.ordinal()));
        testRecommends.add(new Message("", "Hi", MessageType.DATE.ordinal()));
        testRecommends.add(new Message("", "Hello", MessageType.REMOTE.ordinal()));
        testRecommends.add(new Message("", "HelloHelloHelloHelloHelloHello", MessageType.LOCAL.ordinal()));
        testRecommends.add(new Message("", "Hello\nHello\nHello\nHello", MessageType.REMOTE.ordinal()));
        testRecommends.add(new Message("", "HelloHelloHelloHello\nHelloHelloHelloHello\nHelloHelloHelloHello", MessageType.REMOTE.ordinal()));
        testRecommends.add(new Message("", "H", MessageType.LOCAL.ordinal()));
        testRecommends.add(new Message("", "Hi", MessageType.LOCAL.ordinal()));
        testRecommends.add(new Message("", "Hello", MessageType.DATE.ordinal()));
        testRecommends.add(new Message("", "HelloHelloHelloHelloHelloHello", MessageType.LOCAL.ordinal()));
        testRecommends.add(new Message("", "Hello\nHello\nHello\nHello", MessageType.REMOTE.ordinal()));
        testRecommends.add(new Message("", "HelloHelloHelloHello\nHelloHelloHelloHello\nHelloHelloHelloHello", MessageType.REMOTE.ordinal()));


        Logger.d("ChatDetailFragment initViews");
        mChatAdapter.addItems(testRecommends);

    }


}
