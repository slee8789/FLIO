package com.fund.flio.ui.main.message.reply.list;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemReplyListViewModel {

    public ObservableField<String> comment = new ObservableField<>();
    public ObservableField<String> writer = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> imageUserUrl = new ObservableField<>();
    public ChatRoom mChatRoom;

    public ItemReplyListViewModel(ChatRoom chatRoom) {
        Logger.d("ItemChatViewModel " + chatRoom);
        this.mChatRoom = chatRoom;
        writer.set("홍길동");
        this.comment.set("오 저도 기대되는 행사네요");
        this.time.set("2019-01-01");
        imageUserUrl.set("https://picsum.photos/170/170");
    }


}
