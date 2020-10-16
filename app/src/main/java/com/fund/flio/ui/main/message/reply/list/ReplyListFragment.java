package com.fund.flio.ui.main.message.reply.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Certificate;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Reply;
import com.fund.flio.databinding.FragmentReplyListBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.message.chat.list.ChatListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class ReplyListFragment extends BaseFragment<FragmentReplyListBinding, ReplyListViewModel> {

    @Inject
    ReplyListAdapter mReplyListAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reply_list;
    }

    @Override
    public ReplyListViewModel getViewModel() {
        return getViewModelProvider().get(ReplyListViewModel.class);
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
    }

    private void initViews() {
        getViewDataBinding().replies.setAdapter(mReplyListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_gray)));
        getViewDataBinding().replies.addItemDecoration(dividerItemDecoration);
        ArrayList<Reply> dummyCertificates = new Gson().fromJson(readAssetJson(getContext(), "replies.json"), new TypeToken<List<Reply>>() {
        }.getType());
        mReplyListAdapter.setItems(dummyCertificates);
//        mReplyListAdapter.setChatListViewModel(getViewModel());
//        getViewModel().getChatRooms().observe(getViewLifecycleOwner(), chatRoomObserver);
    }


}
