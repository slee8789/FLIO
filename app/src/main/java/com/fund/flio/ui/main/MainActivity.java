package com.fund.flio.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import gun0912.tedkeyboardobserver.TedRxKeyboardObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;
import static com.fund.flio.core.AppConstant.RC_KAKAO_SIGN_IN;


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

    private boolean FAB_Status = false;

    private Animation show_fab_1;
    private Animation hide_fab_1;
    private Animation show_fab_2;
    private Animation hide_fab_2;
    private Animation show_fab_3;
    private Animation hide_fab_3;

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
        mNavController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, mNavController);
        mNavController.addOnDestinationChangedListener(this);
        getViewDataBinding().navigationBottom.setOnNavigationItemReselectedListener(menuItem -> {
        });
        getViewDataBinding().navigationBottom.setItemIconTintList(null);

        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

        getViewDataBinding().fabWrite.setOnClickListener(v -> {
            if (FAB_Status == false) {
                //Display FAB menu
                expandFAB();
                FAB_Status = true;
            } else {
                //Close FAB menu
                hideFAB();
                FAB_Status = false;
            }
        });

    }

    private void expandFAB() {

        //Floating Action Button 1
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (getViewDataBinding().fab1.getWidth() * 0.8);
        layoutParams.bottomMargin += (int) (getViewDataBinding().fab1.getHeight() * 0.25);
        getViewDataBinding().fab1.setLayoutParams(layoutParams);
        getViewDataBinding().fab1.startAnimation(show_fab_1);
        getViewDataBinding().fab1.setClickable(true);

        //Floating Action Button 2
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (getViewDataBinding().fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (getViewDataBinding().fab2.getHeight() * 1.5);
        getViewDataBinding().fab2.setLayoutParams(layoutParams2);
        getViewDataBinding().fab2.startAnimation(show_fab_2);
        getViewDataBinding().fab2.setClickable(true);

        //Floating Action Button 3
        CoordinatorLayout.LayoutParams layoutParams3 = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (getViewDataBinding().fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (getViewDataBinding().fab3.getHeight() * 1.7);
        getViewDataBinding().fab3.setLayoutParams(layoutParams3);
        getViewDataBinding().fab3.startAnimation(show_fab_3);
        getViewDataBinding().fab3.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button 1
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (getViewDataBinding().fab1.getWidth() * 0.8);
        layoutParams.bottomMargin -= (int) (getViewDataBinding().fab1.getHeight() * 0.25);
        getViewDataBinding().fab1.setLayoutParams(layoutParams);
        getViewDataBinding().fab1.startAnimation(hide_fab_1);
        getViewDataBinding().fab1.setClickable(false);

        //Floating Action Button 2
        CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (getViewDataBinding().fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (getViewDataBinding().fab2.getHeight() * 1.5);
        getViewDataBinding().fab2.setLayoutParams(layoutParams2);
        getViewDataBinding().fab2.startAnimation(hide_fab_2);
        getViewDataBinding().fab2.setClickable(false);

        //Floating Action Button 3
        CoordinatorLayout.LayoutParams layoutParams3 = (CoordinatorLayout.LayoutParams) getViewDataBinding().fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (getViewDataBinding().fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (getViewDataBinding().fab3.getHeight() * 1.7);
        getViewDataBinding().fab3.setLayoutParams(layoutParams3);
        getViewDataBinding().fab3.startAnimation(hide_fab_3);
        getViewDataBinding().fab3.setClickable(false);
    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
//        Logger.d("MainActivity authenticationObserver " + authenticationState + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getNavigatorName() + ", " + Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getLabel());
        switch (authenticationState) {
            case NONE:
                switch (Navigation.findNavController(this, R.id.fragment_container).getCurrentDestination().getId()) {
                    case R.id.nav_home:
                        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.action_global_to_nav_intro);
                        break;

                }


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
            case R.id.nav_intro:
            case R.id.nav_login:
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
                getViewDataBinding().fabWrite.setVisibility(View.INVISIBLE);
                break;
            case R.id.nav_market_product:
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().fabWrite.setVisibility(View.INVISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                break;

            case R.id.nav_chat_detail:
            case R.id.nav_sell_list:
            case R.id.nav_search:
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().fabWrite.setVisibility(View.INVISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.GONE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                break;

            case R.id.nav_my_page:
            case R.id.nav_market:
            case R.id.nav_community:
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().fabWrite.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
                break;


            case R.id.nav_home:
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().fabWrite.setVisibility(View.INVISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_white);
                break;

            default:
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getViewDataBinding().fabWrite.setVisibility(View.INVISIBLE);
                getViewDataBinding().navigationBottom.setVisibility(View.VISIBLE);
                getViewDataBinding().navigationBottom.setBackgroundResource(R.drawable.bottom_navigation_background_gray);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
