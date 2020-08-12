package com.fund.flio.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.databinding.ActivityMainBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseActivity;
import com.fund.flio.ui.main.home.HomeViewModel;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.Session;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;
import static com.fund.flio.core.AppConstant.RC_KAKAO_SIGN_IN;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements HasAndroidInjector, NavController.OnDestinationChangedListener {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    DataManager dataManager;

    private NavController mNavController;

    private LoginViewModel loginViewModel;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return getViewModelProvider().get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getViewModel().setNavigator(this);
        initViews();
        loginViewModel = getViewModelProvider().get(LoginViewModel.class);
        loginViewModel.getAuthenticationState().observe(this, authenticationObserver);
        loginViewModel.authenticate(AuthType.valueOf(dataManager.getAuthType()) != AuthType.NONE);

    }

    private void initViews() {
        mNavController = Navigation.findNavController(this, R.id.fragment_container);
//        NavigationUI.setupWithNavController(getViewDataBinding().toolbar, mNavController);
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, mNavController);
        mNavController.addOnDestinationChangedListener(this);
        getViewDataBinding().navigationBottom.setOnNavigationItemReselectedListener(menuItem -> {
        });
        getViewDataBinding().navigationBottom.setItemIconTintList(null);
    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("MainActivity authenticationObserver " + authenticationState);
        if (authenticationState == AuthenticationState.UNAUTHENTICATED) {
            Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_global_to_nav_intro);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("MainActivity onActivityResult " + requestCode + ", " + resultCode);
        switch (requestCode) {
            case RC_KAKAO_SIGN_IN:
                Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data);
                break;

            case RC_GOOGLE_SIGN_IN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Logger.d("LoginActivity account " + account);
                    getViewModel().firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
//                    handleError(e);

                }
                break;

        }
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.nav_intro:
            case R.id.nav_login:
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
//                getViewDataBinding().toolbar.setVisibility(View.GONE);
                break;

            default:
//                getViewDataBinding().toolbar.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Logger.d("onBackPressed " + mNavController.getCurrentDestination().getLabel());
        switch (mNavController.getCurrentDestination().getId()) {
//            case R.id.nav_intro:
//            case R.id.nav_login:
//                mNavController.popBackStack(R.id.nav_home, true);
//                break;
//
//            case R.id.nav_home:

            default:
                super.onBackPressed();
        }
    }


}
