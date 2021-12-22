package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GamesView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView
import javax.inject.Inject

class GamesPresenter(val uiScheduler: Scheduler, val season: Season, val week: Week): MvpPresenter<GamesView>() {

    @Inject lateinit var repo: IGamesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class GamesListPresenter: IGamesListPresenter {
        val games = mutableListOf<Game>()
        override var itemClickListener: ((GameItemView) -> Unit)? = null

        override fun bindView(view: GameItemView) {
            val game = games[view.pos]
            with(view) {
                with(game) {
                    setHome(home.alias)
                    setAway(away.alias)
                    setScoring("${scoring?.homePoints} : ${scoring?.awayPoints}")
                    setScheduled(scheduled)
                    setStatus(status)
                }
            }
        }

        override fun getCount(): Int = games.size
    }

    val gamesListPresenter = GamesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        gamesListPresenter.itemClickListener = {
            val game = gamesListPresenter.games[it.pos]
            router.navigateTo(screens.game(game))
        }
    }

    private fun loadData() {
        repo.getGames(season, week)
            .observeOn(uiScheduler)
            .subscribe({
                gamesListPresenter.games.clear()
                gamesListPresenter.games.addAll(it)
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