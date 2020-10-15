package com.fund.flio.ui.main.market.register.category;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemCategoryViewModel {

    public ObservableField<String> category = new ObservableField<>();
    public int position;

    public ItemCategoryViewModel(int position, String category) {
        this.category.set(category);
        this.position = position;

    }


}
