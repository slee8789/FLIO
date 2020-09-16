package com.fund.flio.ui.main.message.chat.detail;


import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.bus.MessageBus;
import com.fund.flio.data.enums.MessageType;
import com.fund.flio.data.model.Message;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.ChatInsertBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.fund.flio.utils.ViewUtils.readAssetJson;

public class ChatDetailViewModel extends BaseViewModel {

    private int mChatSeq;

    public ObservableField<String> inputMessage = new ObservableField<>();

    private MutableLiveData<List<Message>> messages = new MutableLiveData<>();

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public ChatDetailViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        subscribeEvent();

//        ArrayList<Message> dummyMessages = new Gson().fromJson(readAssetJson(context, "messages.json"), new TypeToken<List<Message>>() {
//        }.getType());
//        messages.setValue(dummyMessages);
    }

    private void subscribeEvent() {
        getCompositeDisposable().add(MessageBus.getInstance().getMessage()
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    messages.getValue().add(message);
                    messages.setValue(messages.getValue());
                }));
    }

    public void selectMyChatDetail(int chatSeq) {
        mChatSeq = chatSeq;
        getCompositeDisposable().add(getDataManager().selectMyChatDetail(new ChatDetailBody(chatSeq))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(messages -> {
                    if (messages.isSuccessful()) {
                        this.messages.setValue(messages.body().getMessages());
                    }
                }, onError -> Logger.e("messages error " + onError)));
    }

    public void goBack(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigateUp();
    }


    public void onSend(View v) {
        getCompositeDisposable().add(getDataManager().insertMyChatDetail(new ChatInsertBody(mChatSeq, inputMessage.get(), null))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(Void -> {
                    messages.getValue().add(new Message(new Random().nextInt(), "123", inputMessage.get(), MessageType.LOCAL.ordinal()));
                    messages.setValue(messages.getValue());
                    inputMessage.set("");
                }));

    }

}
