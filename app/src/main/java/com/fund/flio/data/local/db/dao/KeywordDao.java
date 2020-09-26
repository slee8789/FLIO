package com.fund.flio.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fund.flio.data.model.Keyword;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface KeywordDao {

    @Insert
    long insert(Keyword keyword);

    @Query("SELECT * FROM Keyword")
    Observable<List<Keyword>> loadAll();

    @Query("SELECT * FROM Keyword WHERE keyword == :keyword ")
    Observable<Keyword> isExist(String keyword);

    @Delete
    void delete(Keyword keyword);

}
