package com.fund.flio.ui.main.message.chat.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.databinding.ItemChatBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ChatRoom> chatRooms;
    private ChatListViewModel chatListViewModel;

    public void setChatListViewModel(ChatListViewModel chatListViewModel) {
        this.chatListViewModel = chatListViewModel;
    }

    public ChatListAdapter(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void setItems(List<ChatRoom> chatRooms) {
        final ChatListAdapter.ChatRoomDiffCallback diffCallback = new ChatListAdapter.ChatRoomDiffCallback(this.chatRooms, chatRooms);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.chatRooms.clear();
        this.chatRooms.addAll(chatRooms);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatBinding itemChatBinding = ItemChatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChatRoomViewHolder(itemChatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }

    public class ChatRoomViewHolder extends BaseViewHolder {

        ItemChatBinding itemChatBinding;

        public ChatRoomViewHolder(ItemChatBinding binding) {
            super(binding.getRoot());
            this.itemChatBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final ChatRoom chatRoom = chatRooms.get(position);
            Logger.d("onBind " + position + ", " + chatRoom);
            ItemChatListViewModel itemChatViewModel = new ItemChatListViewModel(chatRoom);
            itemChatBinding.setItemViewModel(itemChatViewModel);
            itemChatBinding.setViewModel(chatListViewModel);

        }
    }

    private static class ChatRoomDiffCallback extends DiffUtil.Callback {
        private final List<ChatRoom> oldChatRooms;
        private final List<ChatRoom> newChatRooms;

        public ChatRoomDiffCallback(List<ChatRoom> oldChatRooms, List<ChatRoom> newChatRooms) {
            this.oldChatRooms = oldChatRooms;
            this.newChatRooms = newChatRooms;
        }

        @Override
        public int getOldListSize() {
            return oldChatRooms.size();
        }

        @Override
        public int getNewListSize() {
            return newChatRooms.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldChatRooms.get(oldItemPosition).getChatSeq() == newChatRooms.get(newItemPosition).getChatSeq();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final ChatRoom oldChatRoom = oldChatRooms.get(oldItemPosition);
            final ChatRoom newChatRoom = newChatRooms.get(newItemPosition);
            if(oldChatRoom.getChatSeq() == 6) {
                Logger.d("areContentsTheSame " + oldChatRoom);
                Logger.d("areContentsTheSame " + newChatRoom);
                Logger.d("areContentsTheSame " + (oldChatRoom.equals(newChatRoom)));
            }

            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
