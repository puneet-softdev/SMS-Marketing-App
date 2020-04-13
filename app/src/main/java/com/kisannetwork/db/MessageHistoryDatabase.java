package com.kisannetwork.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.kisannetwork.db.dao.MessageHistoryDao;
import com.kisannetwork.db.entity.MessageHistoryEntity;

@Database(entities = {MessageHistoryEntity.class}, version = 1)
public abstract class MessageHistoryDatabase extends RoomDatabase {

    private static MessageHistoryDatabase sInstance;

    public static final String DATABASE_NAME = "message_history_db";

    public abstract MessageHistoryDao messageHistoryDao();

    public static MessageHistoryDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (MessageHistoryDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MessageHistoryDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
