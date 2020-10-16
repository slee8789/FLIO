package com.fund.flio.ui.main.mypage.setting;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.databinding.FragmentMyPageBinding;
import com.fund.flio.databinding.FragmentSettingBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;


public class SettingFragment extends BaseFragment<FragmentSettingBinding, SettingViewModel> {

    @Inject
    DataManager dataManager;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public SettingViewModel getViewModel() {
        return getViewModelProvider().get(SettingViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupActionBar();
        getViewDataBinding().switchAll.setChecked(dataManager.notifyEvery());
        getViewDataBinding().switchChat.setChecked(dataManager.notifyChat());
        getViewDataBinding().switchAll.setOnCheckedChangeListener((compoundButton, b) -> {
            Logger.d("switchAll " + b);
            dataManager.setNotifyChat(b);
            getViewDataBinding().switchChat.setChecked(b);
        });
        getViewDataBinding().switchChat.setOnCheckedChangeListener((compoundButton, b) -> {
            Logger.d("switchChat " + b);
            dataManager.setNotifyChat(b);
        });
    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d("onOptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                Logger.d("onOptionsItemSelected home");
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
