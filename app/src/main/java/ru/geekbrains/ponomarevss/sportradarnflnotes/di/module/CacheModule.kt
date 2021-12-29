package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomSeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomWeeksCache
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
    fun conferencesCache(db: Database): IConferencesCache = RoomConferencesCache(db)

    @Singleton
    @Provides
    fun seasonCache(db: Database): ISeasonsCache = RoomSeasonsCache(db)

    @Singleton
    @Provides
    fun weekCache(db: Database): IWeeksCache = RoomWeeksCache(db)

    @Singleton
    @Provides
    fun gameCache(db: Database): IGamesCache = RoomGamesCache(db)
}