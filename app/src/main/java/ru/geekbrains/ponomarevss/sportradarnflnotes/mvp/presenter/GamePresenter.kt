package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Period
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IConferencesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IPeriodsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GameView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.PeriodItemView
import javax.inject.Inject

class GamePresenter(val uiScheduler: Scheduler, val game: Game): MvpPresenter<GameView>() {

    @Inject lateinit var repo: IConferencesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class PeriodsListPresenter: IPeriodsListPresenter {
        val periods = mutableListOf<Period>()
        override var itemClickListener: ((PeriodItemView) -> Unit)? = null

        override fun bindView(view: PeriodItemView) {
            val period = periods[view.pos]
            with(view) {
                with(period) {
                    setPeriodType(periodType)
                    setNumber(number.toString())
                    setHomePoints(homePoints.toString())
                    setAwayPoints(awayPoints.toString())
                }
            }
        }

        override fun getCount(): Int = periods.size
    }

    val periodsListPresenter = PeriodsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setScheduled(game.scheduled)
        viewState.setStatus(game.status)
        loadTeamsData()
        viewState.setHomePoints(game.scoring?.homePoints.toString())
        viewState.setAwayPoints(game.scoring?.awayPoints.toString())
        loadPeriodsData()
    }

    private fun loadTeamsData() {
        repo.getTeam(game.home.id)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.setHomeName(it.name)
                viewState.setHomeMarket(it.market)
            }, {
                println(it.message)
            })
        repo.getTeam(game.away.id)
            .observeOn(uiScheduler)
            .subscribe({
                viewState.setAwayName(it.name)
                viewState.setAwayMarket(it.market)
            }, {
                println(it.message)
            })
    }

    private fun loadPeriodsData() {
        periodsListPresenter.periods.clear()
        game.scoring?.periods?.let { periodsListPresenter.periods.addAll(it) }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}