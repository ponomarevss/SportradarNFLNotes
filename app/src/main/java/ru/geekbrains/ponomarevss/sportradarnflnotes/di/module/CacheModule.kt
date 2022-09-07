package ru.geekbrains.ponomarevss.sportradarnflnotes.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

//    @Singleton
//    @Provides
//    fun database(app: App): SportradarDatabase = Room.databaseBuilder(
//        app,
//        SportradarDatabase::class.java,
//        SportradarDatabase.DB_NAME
//    ).build()

//    @Singleton
//    @Provides
//    fun teamsCache(db: SportradarDatabase): ITeamsCache = RoomTeamsCache(db)

//    @Singleton
//    @Provides
//    fun seasonCache(db: SportradarDatabase): ISeasonsCache = RoomSeasonsCache(db)

//    @Singleton
//    @Provides
//    fun weekCache(db: SportradarDatabase): IWeeksCache = RoomWeeksCache(db)

//    @Singleton
//    @Provides
//    fun gameCache(db: SportradarDatabase): IGamesCache = RoomGamesCache(db)

//    @Singleton
//    @Provides
//    fun standingsCache(db: SportradarDatabase): IStandingsCache = RoomStandingsCache(db)
}