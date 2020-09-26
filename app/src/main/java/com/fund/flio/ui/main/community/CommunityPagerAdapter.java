package com.fund.flio.ui.main.community;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fund.flio.ui.main.community.certificate.list.CertificateListFragment;
import com.fund.flio.ui.main.community.event.list.EventListFragment;
import com.fund.flio.ui.main.community.news.NewsFragment;

public class CommunityPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public CommunityPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewsFragment tab1 = new NewsFragment();
                return tab1;
            case 1:
                CertificateListFragment tab2 = new CertificateListFragment();
                return tab2;
            case 2:
                EventListFragment tab3 = new EventListFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
