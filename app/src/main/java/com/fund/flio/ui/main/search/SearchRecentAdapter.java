package com.fund.flio.ui.main.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.SearchResult;
import com.fund.flio.databinding.ItemChatBinding;
import com.fund.flio.databinding.ItemSearchRecentBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.List;

public class SearchRecentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<SearchResult> searchResults;
    private SearchViewModel searchViewModel;

    public void setSearchViewModel(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    public SearchRecentAdapter(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    public void setItems(List<SearchResult> chatRooms) {
        final SearchResultDiffCallback diffCallback = new SearchResultDiffCallback(this.searchResults, chatRooms);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.searchResults.clear();
        this.searchResults.addAll(chatRooms);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchRecentBinding itemSearchRecentBinding = ItemSearchRecentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChatRoomViewHolder(itemSearchRecentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ChatRoomViewHolder extends BaseViewHolder {

        ItemSearchRecentBinding itemSearchRecentBinding;

        public ChatRoomViewHolder(ItemSearchRecentBinding binding) {
            super(binding.getRoot());
            this.itemSearchRecentBinding = binding;
        }


        @Override
        public void onBind(int position) {
            final SearchResult searchResult = searchResults.get(position);
            Logger.d("onBind " + position + ", " + searchResult);
            ItemSearchRecentViewModel itemChatViewModel = new ItemSearchRecentViewModel(searchResult);
            itemSearchRecentBinding.setItemViewModel(itemChatViewModel);
            itemSearchRecentBinding.setViewModel(searchViewModel);

        }
    }

    private static class SearchResultDiffCallback extends DiffUtil.Callback {
        private final List<SearchResult> oldSearchResult;
        private final List<SearchResult> newSearchResult;

        public SearchResultDiffCallback(List<SearchResult> oldSearchResult, List<SearchResult> newSearchResult) {
            this.oldSearchResult = oldSearchResult;
            this.newSearchResult = newSearchResult;
        }

        @Override
        public int getOldListSize() {
            return oldSearchResult.size();
        }

        @Override
        public int getNewListSize() {
            return newSearchResult.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldSearchResult.get(oldItemPosition).getId() == newSearchResult.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final SearchResult oldChatRoom = oldSearchResult.get(oldItemPosition);
            final SearchResult newChatRoom = newSearchResult.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}
