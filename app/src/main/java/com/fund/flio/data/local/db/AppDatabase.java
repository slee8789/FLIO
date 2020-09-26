package com.fund.flio.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fund.flio.data.local.db.dao.KeywordDao;
import com.fund.flio.data.local.db.dao.SearchResultDao;
import com.fund.flio.data.model.Keyword;
import com.fund.flio.data.model.SearchResult;

@Database(entities = {SearchResult.class, Keyword.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SearchResultDao searchResultDao();

    public abstract KeywordDao keywordDao();

}
