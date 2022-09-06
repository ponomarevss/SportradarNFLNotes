package ru.geekbrains.ponomarevss.sportradarnflnotes.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.SeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.navigation.AndroidScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

val application = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
    single<IScreens> { AndroidScreens() }
}

val seasonsFragment = module {
    single<ISeasonsRepo> { SeasonsRepo() }
    viewModel { SeasonsViewModel(get()) }
}

val api = module {
    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
    single<IDataSource> {
        Retrofit.Builder()
            .baseUrl(NAME_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(IDataSource::class.java)
    }

}