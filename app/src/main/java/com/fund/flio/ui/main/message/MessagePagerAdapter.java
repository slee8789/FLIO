package com.fund.flio.ui.main.message;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fund.flio.ui.main.message.reply.list.ReplyListFragment;
import com.fund.flio.ui.main.message.chat.list.ChatListFragment;

public class MessagePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MessagePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ChatListFragment tab1 = new ChatListFragment();
                return tab1;
            case 1:
                ReplyListFragment tab2 = new ReplyListFragment();
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
