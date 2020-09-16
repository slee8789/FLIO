package com.fund.flio.ui.main.mypage.selllist;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fund.flio.ui.main.message.alarm.AlarmFragment;
import com.fund.flio.ui.main.message.chat.list.ChatListFragment;
import com.fund.flio.ui.main.mypage.selled.SelledFragment;
import com.fund.flio.ui.main.mypage.selling.SellingFragment;

public class SellListPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public SellListPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SellingFragment tab1 = new SellingFragment();
                return tab1;
            case 1:
                SelledFragment tab2 = new SelledFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
