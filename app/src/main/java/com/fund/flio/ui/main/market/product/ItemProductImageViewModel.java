package com.fund.flio.ui.main.market.product;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.databinding.ItemProductHomeBinding;
import com.fund.flio.databinding.ItemProductSelledBinding;
import com.fund.flio.databinding.ItemProductSellingBinding;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.MarketFragmentDirections;
import com.orhanobut.logger.Logger;

public class ItemProductImageViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemProductImageViewModel(String product) {
        Logger.d("ItemProductImageViewModel " + product);
        imageUrl.set(product);

    }


    public void onItemClick(View v) {

    }

}
