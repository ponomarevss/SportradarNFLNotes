package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IStandingsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.StandingsItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView
import javax.inject.Inject

class SeasonPresenter(val uiScheduler: Scheduler, val season: Season): MvpPresenter<WeeksView>() {

    @Inject lateinit var weeksRepo: IWeeksRepo
    @Inject lateinit var standingsRepo: IStandingsRepo
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

    class StandingsListPresenter: IStandingsListPresenter {
        val standingsList = mutableListOf<Standings>()
        override var itemClickListener: ((StandingsItemView) -> Unit)? = null

        override fun bindView(view: StandingsItemView) {
            val standings = standingsList[view.pos]
            with (view) {
                with (standings) {
                    setTeam(teamId)
                    setWLT("${wins}-${losses}-${ties}")
                    setDivWLT("${divWins}-${divLosses}-${divTies}")
                }
            }
        }

        override fun getCount(): Int = standingsList.size
    }

    val weeksListPresenter = WeeksListPresenter()
    val standingsListPresenter = StandingsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadWeeksData()
        loadStandingsData()

        weeksListPresenter.itemClickListener = {
            val week = weeksListPresenter.weeks[it.pos]
            router.navigateTo(screens.games(season, week))
        }
    }



    private fun loadWeeksData() {
        weeksRepo.getWeeks(season)
            .observeOn(uiScheduler)
            .subscribe({
                weeksListPresenter.weeks.clear()
                weeksListPresenter.weeks.addAll(it)
                viewState.updateList()
            },{
                println(it.message)
            })
    }

    private fun loadStandingsData() {
        standingsRepo.getStandingsList(season.id)
            .observeOn(uiScheduler)
            .subscribe({
                standingsListPresenter.standingsList.clear()
                standingsListPresenter.standingsList.addAll(it)
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