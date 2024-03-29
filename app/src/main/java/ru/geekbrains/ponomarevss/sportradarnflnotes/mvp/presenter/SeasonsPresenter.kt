package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Type
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ISeasonsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.MainView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.SeasonsView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView
import javax.inject.Inject

class SeasonsPresenter(val uiScheduler: Scheduler): MvpPresenter<SeasonsView>() {

    @Inject lateinit var repo: ISeasonsRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class SeasonsListPresenter: ISeasonsListPresenter{
        val seasons = mutableListOf<Season>()
        override var itemClickListener: ((SeasonItemView) -> Unit)? = null

        override fun bindView(view: SeasonItemView) {
            val season = seasons[view.pos]
            with(view) {
                with(season) {
                    setYear(year.toString())
                    setStartDate(startDate)
                    setEndDate(endDate)
                    setStatus(status)
                    setType(type.code)
                }
            }
        }

        override fun getCount(): Int = seasons.size
    }

    val seasonsListPresenter = SeasonsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        seasonsListPresenter.itemClickListener = {
            val season = seasonsListPresenter.seasons[it.pos]
            router.navigateTo(screens.weeks(season))
        }
    }

    private fun loadData() {
        repo.getSeasons()
            .observeOn(uiScheduler)
            .subscribe({
                seasonsListPresenter.seasons.clear()
                seasonsListPresenter.seasons.addAll(it.asReversed() )
                viewState.updateList()
            },{
                println(it.message)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}