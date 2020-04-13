package com.kisannetwork.db.repository;

import android.app.Application;

import com.kisannetwork.db.MessageHistoryDatabase;
import com.kisannetwork.db.dao.MessageHistoryDao;
import com.kisannetwork.db.entity.MessageHistoryEntity;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class MessageHistoryDbRepository {
    private MessageHistoryDao mMessageHistoryDao;
    private Maybe<List<MessageHistoryEntity>> mAllMessages;

    public MessageHistoryDbRepository(Application application) {
        MessageHistoryDatabase db = MessageHistoryDatabase.getDatabase(application);
        mMessageHistoryDao = db.messageHistoryDao();
    }

    /*
    * Get All Messages from Local Db - Room
    * */
    public Maybe<List<MessageHistoryEntity>> getAllMessages() {
        return mMessageHistoryDao.fetchAllContacts();
    }

    /*
    * Insert Message in Local Db - Room
    * */
    public Single<Long> insert(MessageHistoryEntity message) {
        return mMessageHistoryDao.insert(message);
    }
}
