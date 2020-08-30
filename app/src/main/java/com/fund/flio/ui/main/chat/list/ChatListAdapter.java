package com.fund.flio.ui.main.chat.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Message;
import com.fund.flio.databinding.ItemChatBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.chat.detail.ItemChatViewModel;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Message> messages;
    private ChatListViewModel chatListViewModel;

    public void setChatListViewModel(ChatListViewModel chatListViewModel) {
        this.chatListViewModel = chatListViewModel;
    }

    public ChatListAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public void addItems(List<Message> recommends) {
        Logger.d("ChatAdapter addItems " + recommends.size());
        this.messages.addAll(recommends);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatBinding itemChatBinding = ItemChatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MessageViewHolder(itemChatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (messages != null && messages.size() > 0) {
            return messages.size();
        } else {
            return 1;
        }
    }

    public class MessageViewHolder extends BaseViewHolder {

        ItemChatBinding itemChatBinding;

        public MessageViewHolder(ItemChatBinding binding) {
            super(binding.getRoot());
            this.itemChatBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final Message message = messages.get(position);
            Logger.d("onBind " + position + ", " + message);
            ItemChatListViewModel itemChatViewModel = new ItemChatListViewModel(message);
            itemChatBinding.setItemViewModel(itemChatViewModel);
            itemChatBinding.setViewModel(chatListViewModel);

        }
    }
}
