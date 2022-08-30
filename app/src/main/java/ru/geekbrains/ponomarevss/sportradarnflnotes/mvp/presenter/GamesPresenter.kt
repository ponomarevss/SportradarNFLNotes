package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import org.koin.java.KoinJavaComponent.inject
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GamesView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView
import javax.inject.Inject
import javax.inject.Named

class GamesPresenter(private val uiScheduler: Scheduler, val season: Season, val week: Week) : MvpPresenter<GamesView>() {

    companion object {
        private const val CLOSED_STATUS = "closed"
    }

//    @Inject lateinit var standingsRepo: IStandingsRepo
//    @Inject lateinit var teamsRepo: ITeamsRepo
//    @Inject lateinit var gamesRepo: IGamesRepo

//    @Inject lateinit var router: Router
//    @Inject lateinit var screens: IScreens

    @Inject @field:Named("logoUrl") lateinit var logoUrl: String

    inner class GamesListPresenter : IGamesListPresenter {
        val games = mutableListOf<Game>()
        val teams = mutableListOf<Team>()
        override var itemClickListener: ((GameItemView) -> Unit)? = null

        override fun bindView(view: GameItemView) {
            val game = games[view.pos]
            val home = teams.first { it.id == game.home }
            val away = teams.first { it.id == game.away }
            with(view) {
                setHome(home.alias)
                setAway(away.alias)
                if (game.isWatched) setScoring("${game.homePoints} : ${game.awayPoints}") else setScoring("")
                setScheduled(game.scheduled)
                setStatus(game.status)
                loadHomeLogo(logoUrl + home.alias)
                loadAwayLogo(logoUrl + away.alias)
            }
        }

        override fun getCount(): Int = games.size
    }

    val gamesListPresenter = GamesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadGamesData()
        loadTeamsData()

//        gamesListPresenter.itemClickListener = {
//            val game = gamesListPresenter.games[it.pos]
//            game.apply {
//                if (status == CLOSED_STATUS && !isWatched) {
//                    isWatched = true
//                    gamesRepo.putGame(this, week).observeOn(uiScheduler).subscribe()
//                    updateStandings(season.id, game)
//                }
//            }
//            viewState.updateList()
//        }
    }

    private fun loadGamesData() {
//        gamesRepo.getGames(season, week)
//            .observeOn(uiScheduler)
//            .subscribe({
//                gamesListPresenter.games.clear()
//                gamesListPresenter.games.addAll(it)
//                viewState.updateList()
//            }, {
//                println(it.message)
//            })
    }

    private fun loadTeamsData() {
//        teamsRepo.getTeams()
//            .observeOn(uiScheduler)
//            .subscribe({
//                gamesListPresenter.teams.clear()
//                gamesListPresenter.teams.addAll(it)
//            }, {
//                println(it.message)
//            })
    }

    private fun updateStandings(seasonId: String, game: Game) {
//        val singleHome = teamsRepo.getTeam(game.home)
//        val singleAway = teamsRepo.getTeam(game.away)
//        val singleStandHome = standingsRepo.getStandings(seasonId, game.home)
//        val singleStandAway = standingsRepo.getStandings(seasonId, game.away)
//
//        Single.zip(singleHome, singleAway, singleStandHome, singleStandAway) { home, away, stHome, stAway ->
//            when {
//                game.homePoints > game.awayPoints -> homeWins(home, away, stHome, stAway)
//                game.homePoints < game.awayPoints -> awayWins(home, away, stHome, stAway)
//                game.homePoints == game.awayPoints -> ties(home, away, stHome, stAway)
//            }
//            return@zip listOf(stHome, stAway)
//        }.flatMap {
//            standingsRepo.putStandingsList(it).toSingleDefault(it)
//        }.observeOn(uiScheduler)
//            .subscribe({}, {
//                println(it.message)
//            })
//
    }

    private fun homeWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.wins
        ++stAway.losses
        if (home.division == away.division) {
            ++stHome.divWins
            ++stAway.divLosses
        }
    }

    private fun awayWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.losses
        ++stAway.wins
        if (home.division == away.division) {
            ++stHome.divLosses
            ++stAway.divWins
        }
    }

    private fun ties(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.ties
        ++stAway.ties
        if (home.division == away.division) {
            ++stHome.divTies
            ++stAway.divTies
        }
    }

    fun backPressed(): Boolean {
//        router.navigateTo(screens.season(season))
        return true
    }
//    fun backPressed(): Boolean {
//        router.exit()
//        return true
//    }
}