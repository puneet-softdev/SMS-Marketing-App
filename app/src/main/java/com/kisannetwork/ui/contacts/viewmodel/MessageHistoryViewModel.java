package com.kisannetwork.ui.contacts.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kisannetwork.data.ApiResponse;
import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.db.repository.MessageHistoryDbRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MessageHistoryViewModel extends AndroidViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private MessageHistoryDbRepository messageHistoryDbRepository;
    private final MutableLiveData<List<MessageHistoryEntity>> messagesLiveData = new MutableLiveData<>();

    public MessageHistoryViewModel(@NonNull Application application) {
        super(application);
        messageHistoryDbRepository = new MessageHistoryDbRepository(application);
    }

    public MutableLiveData<List<MessageHistoryEntity>> getMessagesLiveData() {
        return messagesLiveData;
    }

    public void getMessageHistory(){
        disposables.add(messageHistoryDbRepository.getAllMessages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> messagesLiveData.setValue(result),
                        throwable -> messagesLiveData.setValue(null)
                ));
    }
}
