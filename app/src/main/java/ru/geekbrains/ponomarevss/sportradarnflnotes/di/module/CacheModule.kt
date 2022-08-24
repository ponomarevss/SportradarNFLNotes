package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        Database.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun conferencesCache(db: Database): ITeamsCache = RoomTeamsCache(db)

    @Singleton
    @Provides
    fun seasonCache(db: Database): ISeasonsCache = RoomSeasonsCache(db)

    @Singleton
    @Provides
    fun weekCache(db: Database): IWeeksCache = RoomWeeksCache(db)

    @Singleton
    @Provides
    fun gameCache(db: Database): IGamesCache = RoomGamesCache(db)

    @Singleton
    @Provides
    fun standingsCache(db: Database): IStandingsCache = RoomStandingsCache(db)
}