package com.fund.flio.ui.main.message.chat.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Chat;
import com.fund.flio.databinding.ItemChatDateBinding;
import com.fund.flio.databinding.ItemChatLocalBinding;
import com.fund.flio.databinding.ItemChatRemoteBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.message.chat.list.ChatListViewModel;
import com.fund.flio.utils.CommonUtils;
import com.orhanobut.logger.Logger;

import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Chat> chats;

    private ChatDetailViewModel chatDetailViewModel;

    public void setChatDetailViewModel(ChatDetailViewModel chatDetailViewModel) {
        Logger.d("ChatAdapter setChatDetailViewModel " + chatDetailViewModel);
        this.chatDetailViewModel = chatDetailViewModel;
    }

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    public void addItems(List<Chat> chats) {
        Logger.d("ChatAdapter addItems " + chats.size());
        this.chats.addAll(chats);
        notifyDataSetChanged();
    }

    public void setItems(List<Chat> chats) {
        final MessageDiffCallback diffCallback = new MessageDiffCallback(this.chats, chats);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.chats.clear();
        this.chats.addAll(chats);
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
        return chats.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chats.get(position).getChatType();
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
            final Chat chat = chats.get(position);
            ItemChatViewModel itemChatViewModel = new ItemChatViewModel(chat);
            MessageType[] messageTypes = MessageType.values();
            switch (messageTypes[getItemViewType()]) {
                case DATE:
                    chatDateBinding.setViewModel(itemChatViewModel);
                    chatDateBinding.executePendingBindings();

                    break;
                case LOCAL:
                    chatLocalBinding.setItemViewModel(itemChatViewModel);
                    chatLocalBinding.setViewModel(chatDetailViewModel);
                    chatLocalBinding.executePendingBindings();
                    break;
                case REMOTE:
                    chatRemoteBinding.setItemViewModel(itemChatViewModel);
                    chatRemoteBinding.setViewModel(chatDetailViewModel);
                    chatRemoteBinding.executePendingBindings();
                    break;
            }

        }
    }

    private static class MessageDiffCallback extends DiffUtil.Callback {
        private final List<Chat> oldChats;
        private final List<Chat> newChats;

        public MessageDiffCallback(List<Chat> oldChats, List<Chat> newChats) {
            this.oldChats = oldChats;
            this.newChats = newChats;
        }

        @Override
        public int getOldListSize() {
            return oldChats.size();
        }

        @Override
        public int getNewListSize() {
            return newChats.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//            Logger.d("areItemsTheSame " + oldItemPosition + ", " + newItemPosition + ", " + (oldMessages.get(oldItemPosition).getMessageId() == newMessages.get(newItemPosition).getMessageId()));
            return oldChats.get(oldItemPosition).getChatIndex() == newChats.get(newItemPosition).getChatIndex();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Chat oldChat = oldChats.get(oldItemPosition);
            final Chat newChat = newChats.get(newItemPosition);
//            Logger.d("areContentsTheSame " + oldMessage + ", " + newMessage + ", " + (oldMessage.equals(newMessage)));
            return oldChat.equals(newChat);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            // Implement method if you're going to use ItemAnimator
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
