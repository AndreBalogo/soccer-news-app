package br.com.andrebalogo.soccernews.data.local.news;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import br.com.andrebalogo.soccernews.domain.News;

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void save(News news);

    @Query("SELECT * FROM news WHERE favorite = 1")
    LiveData<List<News>> loadFavoriteNews;
}
