package br.com.andrebalogo.soccernews.data.local.news;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.andrebalogo.soccernews.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class SoccerNewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}
