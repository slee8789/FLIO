package com.fund.flio.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fund.flio.BR;
import com.fund.flio.BuildConfig;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.databinding.ActivityMainBinding;
import com.fund.flio.ui.base.BaseActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
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


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements HasAndroidInjector, NavController.OnDestinationChangedListener {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    DataManager dataManager;

    private NavController mNavController;
    private GoogleApiClient googleApiClient;

    private AuthViewModel authViewModel;

    public AuthViewModel getAuthViewModel() {
        return authViewModel;
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

        initViews();
        authViewModel = getViewModelProvider().get(AuthViewModel.class);
        authViewModel.setGoogleApiClient(googleApiClient);
        authViewModel.getAuthenticationState().observe(this, authenticationObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app_bar_default, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        setSupportActionBar(getViewDataBinding().toolbar);
        mNavController = Navigation.findNavController(this, R.id.fragment_container);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_market).build();
//        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, mNavController);
        mNavController.addOnDestinationChangedListener(this);
        getViewDataBinding().navigationBottom.setOnNavigationItemReselectedListener(menuItem -> {
        });
        getViewDataBinding().navigationBottom.setItemIconTintList(null);
    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("MainActivity authenticationObserver " + authenticationState + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getId() + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getLabel());
        switch (authenticationState) {
            case NONE:
                Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_global_to_nav_intro);
                break;
            case UNAUTHENTICATED:
                Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_nav_more_to_nav_login);
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
                getViewDataBinding().toolbar.setVisibility(View.GONE);
                break;

            default:
                getViewDataBinding().toolbar.setVisibility(View.VISIBLE);
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
