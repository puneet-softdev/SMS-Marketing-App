package com.kisannetwork.ui.contacts.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kisannetwork.data.ApiResponse;
import com.kisannetwork.data.TwillioWebService;
import com.kisannetwork.db.MessageHistoryDatabase;
import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.db.repository.MessageHistoryDbRepository;
import com.kisannetwork.model.Contact;
import com.kisannetwork.model.Message;
import com.kisannetwork.repository.SendMessageRepository;

import java.util.Map;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SendMessageViewModel extends AndroidViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Long> responseInsertMessage = new MutableLiveData<>();
    private MessageHistoryDbRepository messageHistoryDbRepository;

    public SendMessageViewModel(@NonNull Application application) {
        super(application);
        messageHistoryDbRepository = new MessageHistoryDbRepository(application);
    }

    public MutableLiveData<ApiResponse> messageResponse() {
        return responseLiveData;
    }

    public MutableLiveData<Long> getResponseInsertMessage() {
        return responseInsertMessage;
    }

    public void hitTwillioApi(Map<String, String> smsData, TwillioWebService gitWebService){
        SendMessageRepository repository = new SendMessageRepository(gitWebService);
        disposables.add(repository.twillioCall(smsData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result-> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    public void insertMessage(MessageHistoryEntity mMessage){
        disposables.add(messageHistoryDbRepository.insert(mMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result-> responseInsertMessage.setValue(result),
                        throwable -> responseInsertMessage.setValue(-1L)
                ));
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
