package com.fund.flio.ui.main;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.orhanobut.logger.Logger;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private FirebaseAuth mFirebaseAuth;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider, FirebaseAuth firebaseAuth) {
        super(dataManager, schedulerProvider, resourceProvider);
        mFirebaseAuth = firebaseAuth;
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Logger.d("firebaseAuthWithGoogle " + acct.getIdToken());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        Logger.d("firebaseAuthWithGoogle " + credential + ", mFirebaseAuth " + mFirebaseAuth + ", serverAuthCode " + acct.getServerAuthCode());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Logger.d("firebaseAuthWithGoogle success");
                    } else {
                        getNavigator().handleError(new Throwable("firebaseAuthWithGoogle failed."));
                    }
                });
    }

    public void googleLogout(){
        Logger.d("googleLogout");
        mFirebaseAuth.signOut();
    }
}
