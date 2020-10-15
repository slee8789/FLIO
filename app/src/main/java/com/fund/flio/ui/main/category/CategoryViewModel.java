package com.fund.flio.ui.main.category;


import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.model.Product;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

public class CategoryViewModel extends BaseViewModel {



    public CategoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
//        recommandProducts(ProductCategory.SPEAKER.name(), SpeakerType.PRO.name());
    }

    public void onCategoryClick(View v, ProductCategory productCategory) {
        Logger.d("HomeViewModel onCategoryClick");
//        bundle.putSerializable("productCategory", productCategory);
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_home_to_nav_market, bundle);


    }

}
