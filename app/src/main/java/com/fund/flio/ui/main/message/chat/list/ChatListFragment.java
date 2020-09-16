package com.fund.flio.ui.main.message.chat.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.databinding.FragmentChatListBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.Observer;

import javax.inject.Inject;

public class ChatListFragment extends BaseFragment<FragmentChatListBinding, ChatListViewModel> {

    @Inject
    ChatListAdapter mChatListAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_list;
    }

    @Override
    public ChatListViewModel getViewModel() {
        return getViewModelProvider().get(ChatListViewModel.class);
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
        getViewDataBinding().chats.setAdapter(mChatListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_gray)));
        getViewDataBinding().chats.addItemDecoration(dividerItemDecoration);
        mChatListAdapter.setChatListViewModel(getViewModel());
        getViewModel().getChatRooms().observe(getViewLifecycleOwner(), chatRoomObserver);
    }

    private final Observer<List<ChatRoom>> chatRoomObserver = chatRooms -> {
        Logger.d("chatRoomObserver " + chatRooms);
        mChatListAdapter.setItems(chatRooms);
    };

}
