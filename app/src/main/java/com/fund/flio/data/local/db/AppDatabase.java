package com.fund.flio.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fund.flio.data.local.db.dao.SearchResultDao;
import com.fund.flio.data.model.SearchResult;

@Database(entities = {SearchResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SearchResultDao searchResultDao();

}
