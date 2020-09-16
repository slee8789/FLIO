package com.fund.flio.ui.main.message.chat.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.enums.MessageType;
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

    public void setItems(List<Message> messages) {
        final MessageDiffCallback diffCallback = new MessageDiffCallback(this.messages, messages);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.messages.clear();
        this.messages.addAll(messages);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageType[] messageTypes = MessageType.values();
        switch (messageTypes[viewType]) {
            case DATE:
                ItemChatDateBinding chatDateBinding = ItemChatDateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MessageViewHolder(chatDateBinding);
            case LOCAL:
                ItemChatLocalBinding chatLocalBinding = ItemChatLocalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MessageViewHolder(chatLocalBinding);
            case REMOTE:
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
        return messages.size();
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
                    chatDateBinding.executePendingBindings();

                    break;
                case 1:
                    chatLocalBinding.setViewModel(itemChatViewModel);
                    chatLocalBinding.executePendingBindings();
                    break;
                case 2:
                    chatRemoteBinding.setViewModel(itemChatViewModel);
                    chatRemoteBinding.executePendingBindings();
                    break;
            }

        }
    }

    private static class MessageDiffCallback extends DiffUtil.Callback {
        private final List<Message> oldMessages;
        private final List<Message> newMessages;

        public MessageDiffCallback(List<Message> oldMessages, List<Message> newMessages) {
            this.oldMessages = oldMessages;
            this.newMessages = newMessages;
        }

        @Override
        public int getOldListSize() {
            return oldMessages.size();
        }

        @Override
        public int getNewListSize() {
            return newMessages.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//            Logger.d("areItemsTheSame " + oldItemPosition + ", " + newItemPosition + ", " + (oldMessages.get(oldItemPosition).getMessageId() == newMessages.get(newItemPosition).getMessageId()));
            return oldMessages.get(oldItemPosition).getMessageId() == newMessages.get(newItemPosition).getMessageId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Message oldMessage = oldMessages.get(oldItemPosition);
            final Message newMessage = newMessages.get(newItemPosition);
//            Logger.d("areContentsTheSame " + oldMessage + ", " + newMessage + ", " + (oldMessage.equals(newMessage)));
            return oldMessage.equals(newMessage);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
