package com.fund.flio.ui.main.intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.databinding.FragmentIntroBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static io.reactivex.annotations.SchedulerSupport.NONE;


public class IntroFragment extends BaseFragment<FragmentIntroBinding, IntroViewModel> {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    DataManager dataManager;

    private AuthViewModel authViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_intro;
    }

    @Override
    public IntroViewModel getViewModel() {
        return getViewModelProvider().get(IntroViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setHasOptionsMenu(true);
        getViewModel().getIntroDelay().observe(this, introDelayObserver);
        authViewModel = ((MainActivity) getBaseActivity()).getAuthViewModel();

    }

    private final Observer<Boolean> introDelayObserver = isIntroFinished -> {
        Logger.d("IntroDelayObserver " + dataManager.getFirebaseToken());
        if (AuthType.valueOf(dataManager.getAuthType()) == AuthType.NONE && dataManager.getFirebaseToken() == null) {
            Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_login);
        } else {
            authViewModel.firebaseLogin(AuthType.valueOf(dataManager.getAuthType()), dataManager.getFirebaseToken());
        }

    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
