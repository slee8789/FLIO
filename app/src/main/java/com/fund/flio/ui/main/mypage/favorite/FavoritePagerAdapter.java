package com.fund.flio.ui.main.mypage.favorite;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fund.flio.ui.main.message.chat.list.ChatListFragment;
import com.fund.flio.ui.main.message.reply.list.ReplyListFragment;
import com.fund.flio.ui.main.mypage.favorite.certificate.FavoriteCertificateFragment;
import com.fund.flio.ui.main.mypage.favorite.event.FavoriteEventFragment;
import com.fund.flio.ui.main.mypage.favorite.market.FavoriteProductFragment;

public class FavoritePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FavoritePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FavoriteProductFragment tab1 = new FavoriteProductFragment();
                return tab1;
            case 1:
                FavoriteCertificateFragment tab2 = new FavoriteCertificateFragment();
                return tab2;
            case 2:
                FavoriteEventFragment tab3 = new FavoriteEventFragment();
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
