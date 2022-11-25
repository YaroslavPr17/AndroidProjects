package com.example.bookadviser;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient mInstance = null;

    private final AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "Electronic Library").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
