package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView
import javax.inject.Inject

class WeeksPresenter(val uiScheduler: Scheduler, val season: Season): MvpPresenter<WeeksView>() {

    @Inject lateinit var repo: IWeeksRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class WeeksListPresenter: IWeeksListPresenter {
        val weeks = mutableListOf<Week>()
        override var itemClickListener: ((WeekItemView) -> Unit)? = null

        override fun bindView(view: WeekItemView) {
            val week = weeks[view.pos]
            view.setTitle(week.title)
        }

        override fun getCount(): Int = weeks.size
    }

    val weeksListPresenter = WeeksListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        weeksListPresenter.itemClickListener = {
            val week = weeksListPresenter.weeks[it.pos]
            router.navigateTo(screens.games(season, week))
        }
    }

    private fun loadData() {
        repo.getWeeks(season)
            .observeOn(uiScheduler)
            .subscribe({
                weeksListPresenter.weeks.clear()
                weeksListPresenter.weeks.addAll(it)
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