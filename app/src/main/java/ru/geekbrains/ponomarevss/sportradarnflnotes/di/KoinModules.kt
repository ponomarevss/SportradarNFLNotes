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
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.ISeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeeksCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomGamesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomSeasonsCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.room.RoomWeeksCache
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

}

val seasonsFragment = module {
    single<ISeasonsCache> { RoomSeasonsCache(get()) }
    single<ISeasonsRepo> { SeasonsRepo(get(), get()) }
    viewModel { SeasonsViewModel(get()) }
}
val seasonFragment = module {
    single<IWeeksCache> { RoomWeeksCache(get()) }
    single<IWeeksRepo> { WeeksRepo(get(), get()) }
    viewModel { SeasonViewModel(get(), get()) }
}
val gamesFragment = module {
    single<IGamesCache> { RoomGamesCache(get()) }
    single<IGamesRepo> { GamesRepo(get(), get()) }
    viewModel { GamesViewModel(get(), get(), get()) }
}