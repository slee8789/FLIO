package com.fund.flio.ui.main;

import android.view.View;

import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.orhanobut.logger.Logger;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider, FirebaseAuth firebaseAuth) {
        super(dataManager, schedulerProvider, resourceProvider);
//        mFirebaseAuth = firebaseAuth;
    }


//    public void googleLogout(){
//        Logger.d("googleLogout");
//        mFirebaseAuth.signOut();
//    }

    public void onSearchClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_search);
    }

    public void onBookmarkClick(View v) {
        Logger.d("onBookmarkClick");
    }
}
