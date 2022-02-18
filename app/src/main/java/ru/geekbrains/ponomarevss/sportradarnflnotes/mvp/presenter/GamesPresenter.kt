package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.*
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IConferencesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IGamesRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GamesView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView
import javax.inject.Inject
import javax.inject.Named

class GamesPresenter(val uiScheduler: Scheduler, val season: Season, val week: Week) : MvpPresenter<GamesView>() {

    companion object {
        private const val SCHEDULED_STATUS = "scheduled"
    }

    @Inject lateinit var standingsRepo: IStandingsRepo
    @Inject lateinit var teamsRepo: IConferencesRepo
    @Inject lateinit var gamesRepo: IGamesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject @field:Named("logoUrl") lateinit var logoUrl: String

    inner class GamesListPresenter : IGamesListPresenter {
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
                    loadHomeLogo(logoUrl + home.alias)
                    loadAwayLogo(logoUrl + away.alias)
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
                if (status != SCHEDULED_STATUS && !isWatched) {
                    isWatched = true
                    gamesRepo.putGame(this, week.id).observeOn(uiScheduler).subscribe()
                    updateStandings(season.id, game)
                }
            }
            viewState.updateList()
//            router.navigateTo(screens.game(game))
        }
    }

    private fun loadData() {
        gamesRepo.getGames(season, week)
            .observeOn(uiScheduler)
            .subscribe({
                gamesListPresenter.games.clear()
                gamesListPresenter.games.addAll(it)
                viewState.updateList()
            }, {
                println(it.message)
            })
    }

    private fun updateStandings(seasonId: String, game: Game) {
        val singleHome = teamsRepo.getTeam(game.home.id)
        val singleAway = teamsRepo.getTeam(game.away.id)
        val singleStandHome = standingsRepo.getStandings(seasonId, game.home.id)
        val singleStandAway = standingsRepo.getStandings(seasonId, game.away.id)

        Single.zip(singleHome, singleAway, singleStandHome, singleStandAway, { home, away, stHome, stAway ->
            game.scoring?.run {
                when {
                    homePoints > awayPoints -> homeWins(home, away, stHome, stAway)
                    homePoints < awayPoints -> awayWins(home, away, stHome, stAway)
                    homePoints == awayPoints -> ties(home, away, stHome, stAway)
                }
            } ?: throw Throwable("Game has no scoring")
            return@zip listOf(stHome, stAway)
        }).flatMap {
            standingsRepo.putStandingsList(it).toSingleDefault(it)
        }.observeOn(uiScheduler)
            .subscribe({},{
                println(it.message)
            })

    }

    private fun homeWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.wins
        ++stAway.losses
        if (home.divisionId == away.divisionId) {
            ++stHome.divWins
            ++stAway.divLosses
        }
    }

    private fun awayWins(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.losses
        ++stAway.wins
        if (home.divisionId == away.divisionId) {
            ++stHome.divLosses
            ++stAway.divWins
        }
    }

    private fun ties(home: Team, away: Team, stHome: Standings, stAway: Standings) {
        ++stHome.ties
        ++stAway.ties
        if (home.divisionId == away.divisionId) {
            ++stHome.divTies
            ++stAway.divTies
        }
    }

    fun backPressed(): Boolean {
        router.navigateTo(screens.season(season))
        return true
    }
//    fun backPressed(): Boolean {
//        router.exit()
//        return true
//    }
}