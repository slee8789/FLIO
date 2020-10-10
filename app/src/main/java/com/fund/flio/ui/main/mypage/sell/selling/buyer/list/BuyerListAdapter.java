package com.fund.flio.ui.main.mypage.sell.selling.buyer.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Buyer;
import com.fund.flio.databinding.ItemBuyerBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

public class BuyerListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Buyer> buyers;
    private BuyerListViewModel buyerListViewModel;

    public void setBuyerListViewModel(BuyerListViewModel chatListViewModel) {
        this.buyerListViewModel = chatListViewModel;
    }

    public BuyerListAdapter(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public void setItems(List<Buyer> buyers) {
        final BuyerDiffCallback diffCallback = new BuyerDiffCallback(this.buyers, buyers);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.buyers.clear();
        this.buyers.addAll(buyers);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBuyerBinding buyerBinding = ItemBuyerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BuyerViewHolder(buyerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return buyers.size();
    }

    public class BuyerViewHolder extends BaseViewHolder {

        ItemBuyerBinding itemBuyerBinding;

        public BuyerViewHolder(ItemBuyerBinding binding) {
            super(binding.getRoot());
            this.itemBuyerBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final Buyer chatRoom = buyers.get(position);
            Logger.d("onBind " + position + ", " + chatRoom);
            ItemBuyerViewModel itemBuyerViewModel = new ItemBuyerViewModel(chatRoom);
            itemBuyerBinding.setItemViewModel(itemBuyerViewModel);
            itemBuyerBinding.setViewModel(buyerListViewModel);

        }
    }

    private static class BuyerDiffCallback extends DiffUtil.Callback {
        private final List<Buyer> oldBuyers;
        private final List<Buyer> newByuers;

        public BuyerDiffCallback(List<Buyer> oldBuyers, List<Buyer> newByuers) {
            this.oldBuyers = oldBuyers;
            this.newByuers = newByuers;
        }

        @Override
        public int getOldListSize() {
            return oldBuyers.size();
        }

        @Override
        public int getNewListSize() {
            return newByuers.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldBuyers.get(oldItemPosition).getTargetUid().equals(newByuers.get(newItemPosition).getTargetUid());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Buyer oldChatRoom = oldBuyers.get(oldItemPosition);
            final Buyer newChatRoom = newByuers.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
