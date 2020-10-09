package com.fund.flio.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.fund.flio.BR;
import com.fund.flio.BuildConfig;
import com.fund.flio.R;
import com.fund.flio.core.FlioApplication;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthenticationState;

import com.fund.flio.databinding.ActivityMainBinding;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseActivity;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import com.hlab.fabrevealmenu.helper.Direction;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import com.kakao.auth.AccessTokenCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.orhanobut.logger.Logger;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;
import static com.fund.flio.core.AppConstant.RC_KAKAO_SIGN_IN;
import static com.fund.flio.utils.ViewUtils.getNavigationBarHeight;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements HasAndroidInjector, NavController.OnDestinationChangedListener {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    DataManager dataManager;

    @Inject
    SchedulerProvider schedulerProvider;

    private NavController mNavController;
    private GoogleApiClient googleApiClient;

    private AuthViewModel authViewModel;

    public AuthViewModel getAuthViewModel() {
        return authViewModel;
    }

    private ProductRegisterViewModel productRegisterViewModel;

    public ProductRegisterViewModel getProductRegisterViewModel() {
        return productRegisterViewModel;
    }

    public NavController getNavController() {
        return mNavController;
    }

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(BuildConfig.GOOGLE_SIGN_URL)
                .requestEmail()
                .build();


        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, connectionResult -> Logger.e("onConnectionFailed " + connectionResult.getErrorMessage()))
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Logger.d("setGoogleApiClient before " + googleApiClient);
        initViews();
        authViewModel = getViewModelProvider().get(AuthViewModel.class);
        productRegisterViewModel = getViewModelProvider().get(ProductRegisterViewModel.class);
        authViewModel.setGoogleApiClient(googleApiClient);
        authViewModel.getAuthenticationState().observe(this, authenticationObserver);
    }

    private void initViews() {
        mNavController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, mNavController);
        mNavController.addOnDestinationChangedListener(this);
        getViewDataBinding().navigationBottom.setOnNavigationItemReselectedListener(menuItem -> {
        });
        getViewDataBinding().navigationBottom.setItemIconTintList(null);

        getViewDataBinding().fabWrite.setOnClickListener(v -> mNavController.navigate(R.id.nav_bottom_sheet_write));
//        Logger.d("test 1 " + getViewDataBinding().navigationBottom.getPaddingBottom());
//        getViewDataBinding().navigationBottom.setPadding(0, 0, 0, getNavigationBarHeight(this));
//        Logger.d("test 2 " + getViewDataBinding().navigationBottom.getPaddingBottom());


    }


    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("MainActivity authenticationObserver " + authenticationState + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getNavigatorName() + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getLabel());
        switch (authenticationState) {
            case NONE:
                switch (Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getId()) {
                    case R.id.nav_home:
                        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_global_to_nav_intro);
                        break;

                }


                break;
            case UNAUTHENTICATED:
                Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_global_to_nav_login);
                break;
            case AUTHENTICATED:
                switch (Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getId()) {
                    case R.id.nav_intro:
                        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_home);
                        break;
                    case R.id.nav_login:
                        getAuthViewModel().setIsLoading(false);
                        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_nav_login_to_nav_home);
                        break;
                }

                break;
            case INVALID_AUTHENTICATION:
                break;
            case KAKAO_EMAIL_NEED_AGREE:
                Session.getCurrentSession().updateScopes(this, Arrays.asList("account_email"), new AccessTokenCallback() {

                    @Override
                    public void onAccessTokenReceived(AccessToken accessToken) {
                        authViewModel.onSessionOpened();
                    }

                    @Override
                    public void onAccessTokenFailure(ErrorResult errorResult) {
                        Logger.e("kakao onAccessTokenFailure " + errorResult);
                    }
                });
                break;
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
                    authViewModel.firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    Logger.e("google sign in error " + e.getMessage());
                    authViewModel.setIsLoading(false);
                }
                break;

        }
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        ((FlioApplication) getApplication()).setCurrentDestinationId(destination.getId());
        switch (destination.getId()) {
            case R.id.nav_market_product:
            case R.id.nav_certificate_detail:
            case R.id.nav_event_detail:
            case R.id.nav_market_product_register:
            case R.id.nav_market_product_register_detail:
            case R.id.nav_market_product_register_purpose:
            case R.id.nav_market_product_register_tag:
            case R.id.nav_profile:
            case R.id.nav_community_certificate_register:
            case R.id.nav_community_event_register:
            case R.id.nav_buyer_guide:
            case R.id.nav_buyer_list:
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                getViewDataBinding().fabWrite.setVisibility(View.GONE);
                break;

            case R.id.nav_chat_detail:
            case R.id.nav_sell_list:
            case R.id.nav_search:
            case R.id.nav_setting:
            case R.id.nav_intro:
            case R.id.nav_login:
                showSystemUI();
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                getViewDataBinding().fabWrite.setVisibility(View.GONE);
                break;

            case R.id.nav_market:
            case R.id.nav_community:
            case R.id.nav_my_page:
                showSystemUI();
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                getViewDataBinding().fabWrite.setVisibility(View.VISIBLE);
                break;

            case R.id.nav_home:
                showSystemUI();
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_white);
                getViewDataBinding().fabWrite.setVisibility(View.GONE);
                break;

            default:
                showSystemUI();
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                getViewDataBinding().fabWrite.setVisibility(View.GONE);
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

            default:
                super.onBackPressed();
        }
    }

}
