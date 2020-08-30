package com.fund.flio.ui.main.chat.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Message;
import com.fund.flio.databinding.ItemChatDateBinding;
import com.fund.flio.databinding.ItemChatLocalBinding;
import com.fund.flio.databinding.ItemChatRemoteBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Message> messages;

    public ChatAdapter(List<Message> messages) {
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
        switch (viewType) {
            case 0:
                ItemChatDateBinding chatDateBinding = ItemChatDateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MessageViewHolder(chatDateBinding);
            case 1:
                ItemChatLocalBinding chatLocalBinding = ItemChatLocalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MessageViewHolder(chatLocalBinding);
            case 2:
            default:
                ItemChatRemoteBinding chatRemoteBinding = ItemChatRemoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MessageViewHolder(chatRemoteBinding);
        }
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

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getMessageType();
    }

    public class MessageViewHolder extends BaseViewHolder {

        private ItemChatDateBinding chatDateBinding;
        private ItemChatLocalBinding chatLocalBinding;
        private ItemChatRemoteBinding chatRemoteBinding;

        public MessageViewHolder(ItemChatDateBinding binding) {
            super(binding.getRoot());
            this.chatDateBinding = binding;
        }

        public MessageViewHolder(ItemChatLocalBinding binding) {
            super(binding.getRoot());
            this.chatLocalBinding = binding;
        }

        public MessageViewHolder(ItemChatRemoteBinding binding) {
            super(binding.getRoot());
            this.chatRemoteBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Message message = messages.get(position);
            Logger.d("onBind " + position + ", " + message);
            ItemChatViewModel itemChatViewModel = new ItemChatViewModel(message);
            switch (getItemViewType()) {
                case 0:
                    chatDateBinding.setViewModel(itemChatViewModel);
                    break;
                case 1:
                    chatLocalBinding.setViewModel(itemChatViewModel);
                    break;
                case 2:
                    chatRemoteBinding.setViewModel(itemChatViewModel);
                    break;
            }

        }
    }
}
