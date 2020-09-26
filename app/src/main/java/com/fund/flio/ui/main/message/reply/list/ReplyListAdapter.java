package com.fund.flio.ui.main.message.reply.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.databinding.ItemReplyBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.message.chat.list.ChatListViewModel;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ReplyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ChatRoom> chatRooms;
    private ChatListViewModel chatListViewModel;

    public void setChatListViewModel(ChatListViewModel chatListViewModel) {
        this.chatListViewModel = chatListViewModel;
    }

    public ReplyListAdapter(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void setItems(List<ChatRoom> chatRooms) {
        final ReplyListAdapter.ChatRoomDiffCallback diffCallback = new ReplyListAdapter.ChatRoomDiffCallback(this.chatRooms, chatRooms);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.chatRooms.clear();
        this.chatRooms.addAll(chatRooms);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReplyBinding itemReplyBinding = ItemReplyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReplyViewHolder(itemReplyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }

    public class ReplyViewHolder extends BaseViewHolder {

        ItemReplyBinding itemReplyBinding;

        public ReplyViewHolder(ItemReplyBinding binding) {
            super(binding.getRoot());
            this.itemReplyBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final ChatRoom chatRoom = chatRooms.get(position);
            Logger.d("onBind " + position + ", " + chatRoom);
            ItemReplyListViewModel itemChatViewModel = new ItemReplyListViewModel(chatRoom);
            itemReplyBinding.setItemViewModel(itemChatViewModel);
//            itemReplyBinding.setViewModel(chatListViewModel);

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
