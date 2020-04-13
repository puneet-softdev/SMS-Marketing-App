package com.kisannetwork.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.model.Contact;
import com.kisannetwork.model.Message;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface MessageHistoryDao {

    /*
    * Insert Query
    * RxJava - Single for Response Type - Either Success or Error
    * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> insert(MessageHistoryEntity message);

    /*
     * Insert Query
     * RxJava - Maybe for Response Type - Either Success or No Rows
     * */

    @Query("SELECT * FROM T_MESSAGE_HISTORY ORDER BY T_MESSAGE_HISTORY.mTimestamp desc")
    Maybe<List<MessageHistoryEntity>> fetchAllContacts();

}
