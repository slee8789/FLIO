package com.fund.flio.ui.main.message.chat.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.core.FlioApplication;
import com.fund.flio.data.model.Chat;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.databinding.FragmentChatDetailBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

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
        if (getArguments().getSerializable("chatRoom") != null) {
            getViewModel().setChatRoom((ChatRoom) getArguments().getSerializable("chatRoom"));
        }

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        if (getArguments().getInt("chatSeq") != 0) {
            getViewModel().selectMyChat(getArguments().getInt("chatSeq"));
        }
        Logger.d("ChatDetailFragment onViewCreated");
        ((FlioApplication) getActivity().getApplication()).setCurrentChatSeq(getViewModel().mChatSeq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("ChatDetailFragment onDestroy");
    }

    private void initViews() {
        getViewDataBinding().chats.setAdapter(mChatAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_chat_item)));
        getViewDataBinding().chats.addItemDecoration(dividerItemDecoration);
        mChatAdapter.setChatDetailViewModel(getViewModel());
        getViewModel().getChats().observe(getViewLifecycleOwner(), messageObserver);
        Logger.d("ChatDetailFragment initViews");

    }

    private final Observer<List<Chat>> messageObserver = messages -> {
        Logger.d("messageObserver " + messages);
        mChatAdapter.setItems(messages);
        getViewDataBinding().chats.scrollToPosition(mChatAdapter.getItemCount() - 1);
    };

}
