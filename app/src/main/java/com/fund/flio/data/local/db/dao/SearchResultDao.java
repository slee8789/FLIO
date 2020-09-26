package com.fund.flio.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fund.flio.data.model.SearchResult;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface SearchResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchResult searchResult);

    @Query("SELECT * FROM SearchResult ORDER BY id DESC LIMIT 5")
    Observable<List<SearchResult>> loadAll();

    @Delete
    void delete(SearchResult searchResult);

    @Query("DELETE FROM SearchResult")
    void deleteAll();
}
