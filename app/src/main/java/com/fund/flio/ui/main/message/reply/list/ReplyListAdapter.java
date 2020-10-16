package com.fund.flio.ui.main.message.reply.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Reply;
import com.fund.flio.databinding.ItemReplyBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

public class ReplyListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Reply> replies;


    public ReplyListAdapter(List<Reply> replies) {
        this.replies = replies;
    }

    public void setItems(List<Reply> chatRooms) {
        final ReplyDiffCallback diffCallback = new ReplyDiffCallback(this.replies, chatRooms);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.replies.clear();
        this.replies.addAll(chatRooms);
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
        return replies.size();
    }

    public class ReplyViewHolder extends BaseViewHolder {

        ItemReplyBinding itemReplyBinding;

        public ReplyViewHolder(ItemReplyBinding binding) {
            super(binding.getRoot());
            this.itemReplyBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final Reply chatRoom = replies.get(position);
            Logger.d("onBind " + position + ", " + chatRoom);
            ItemReplyListViewModel itemChatViewModel = new ItemReplyListViewModel(chatRoom);
            itemReplyBinding.setItemViewModel(itemChatViewModel);
//            itemReplyBinding.setViewModel(chatListViewModel);

        }
    }

    private static class ReplyDiffCallback extends DiffUtil.Callback {
        private final List<Reply> oldReplies;
        private final List<Reply> newReplies;

        public ReplyDiffCallback(List<Reply> oldReplies, List<Reply> newReplies) {
            this.oldReplies = oldReplies;
            this.newReplies = newReplies;
        }

        @Override
        public int getOldListSize() {
            return oldReplies.size();
        }

        @Override
        public int getNewListSize() {
            return newReplies.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldReplies.get(oldItemPosition).getReplyId() == newReplies.get(newItemPosition).getReplyId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Reply oldChatRoom = oldReplies.get(oldItemPosition);
            final Reply newChatRoom = newReplies.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
