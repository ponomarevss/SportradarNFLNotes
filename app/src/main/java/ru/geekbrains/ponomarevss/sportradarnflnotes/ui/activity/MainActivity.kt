package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.R
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IConferencesCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.cache.IWeekCache
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.LeagueHierarchy
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomConference
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomDivision
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.RoomLeague
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.db.Database
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeekRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var api: IDataSource
    @Inject lateinit var db: Database
    @Inject lateinit var cache: IWeekCache
    @Inject lateinit var repo: IWeekRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.appComponent.inject(this)

//        api.getLeagueHierarchy()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                       println(it)
//            },{
//                it.message
//            })

        /**Дергаем сезон и кладем его в БД*/
//        api.getSeasonSchedule("2021", "REG")
//            .flatMap {
//                cache.putWeeks(it).toSingleDefault(it)
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                println(it)
//            },{
//                println(it.message)
//            })

//        cache.getWeek(2018,"REG", 1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                       println(it)
//            },{
//                println(it.message)
//            })

        repo.getWeek(2015, "REG", 7)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       println(it)
            },{
                println(it.message)
            })
    }
}