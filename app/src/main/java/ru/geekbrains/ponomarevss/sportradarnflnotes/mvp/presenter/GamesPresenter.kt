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
import javax.inject.Named

class GamesPresenter(val uiScheduler: Scheduler, val season: Season, val week: Week): MvpPresenter<GamesView>() {

    companion object{
        private const val SCHEDULED_STATUS = "scheduled"
    }

    @Inject lateinit var repo: IGamesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject @field:Named("logoUrl") lateinit var logoUrl: String

    inner class GamesListPresenter: IGamesListPresenter {



        val games = mutableListOf<Game>()
        override var itemClickListener: ((GameItemView) -> Unit)? = null

        override fun bindView(view: GameItemView) {
            val game = games[view.pos]
            with(view) {
                with(game) {
                    setHome(home.alias)
                    setAway(away.alias)
                    if (isWatched) setScoring("${scoring?.homePoints} : ${scoring?.awayPoints}") else setScoring("")
                    setScheduled(scheduled)
                    setStatus(status)
                    loadHomeAvatar(logoUrl + home.alias)
                    loadAwayAvatar(logoUrl + away.alias)
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
            game.apply {
                if (status != SCHEDULED_STATUS) {
                    isWatched = true
                    repo.putGame(this, week.id)
                        .observeOn(uiScheduler)
                        .subscribe()
                }
            }
            viewState.updateList()
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