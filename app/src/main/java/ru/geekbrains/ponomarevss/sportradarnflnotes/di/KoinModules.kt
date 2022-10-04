package ru.geekbrains.ponomarevss.sportradarnflnotes.di

import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.SportradarDatabase
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation.AndroidScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.GamesViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

val application = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single<IScreens> { AndroidScreens() }

    single<Gson> {
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation().create()
    }
    single<IDataSource> {
        Retrofit.Builder().baseUrl(NAME_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get())).build()
            .create(IDataSource::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            SportradarDatabase::class.java,
            SportradarDatabase.DB_NAME
        )
            .build()
    }

    single<ITimestampCache> { RoomTimestampCache(db = get()) }
    single<IGamesCache> { RoomGamesCache(db = get()) }
    single<ITeamsCache> { RoomTeamsCache(db = get()) }
    single<ITeamsRepo> { TeamsRepo(api = get(), cache = get()) }
    single<IStandingsCache> { RoomStandingsCache(db = get()) }
}

val seasonsFragment = module {
    single<ISeasonsCache> { RoomSeasonsCache(db = get()) }
    single<ISeasonsRepo> { SeasonsRepo(api = get(), cache = get()) }
    viewModel { SeasonsViewModel(seasonsRepo = get(), teamsRepo = get(), timestampCache = get()) }
}

val seasonFragment = module {
    single<IWeeksCache> { RoomWeeksCache(db = get()) }
    single<IWeeksRepo> { WeeksRepo(api = get(), weeksCache = get(), gamesCache = get()) }
    viewModel {
        SeasonViewModel(
            season = get(),
            weeksRepo = get(),
            teamsRepo = get(),
            standingsCache = get(),
            timestampCache = get()
        )
    }
}

val gamesFragment = module {
    viewModel {
        GamesViewModel(
            seasonId = get(),
            weekId = get(),
            gamesCache = get(),
            teamsRepo = get(),
            standingsCache = get()
        )
    }
}