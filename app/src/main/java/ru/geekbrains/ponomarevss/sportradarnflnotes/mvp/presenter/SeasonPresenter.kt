package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Team
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.ITeamsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IStandingsRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.repo.IWeeksRepo
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IStandingsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.StandingsItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView
import javax.inject.Inject
import javax.inject.Named

class SeasonPresenter(val uiScheduler: Scheduler, val season: Season): MvpPresenter<WeeksView>() {

    @Inject lateinit var weeksRepo: IWeeksRepo
    @Inject lateinit var standingsRepo: IStandingsRepo
    @Inject lateinit var teamsRepo: ITeamsRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject @field:Named("logoUrl") lateinit var logoUrl: String


    class WeeksListPresenter: IWeeksListPresenter {
        val weeks = mutableListOf<Week>()
        override var itemClickListener: ((WeekItemView) -> Unit)? = null

        override fun bindView(view: WeekItemView) {
            val week = weeks[view.pos]
            view.setTitle(week.title)
        }

        override fun getCount(): Int = weeks.size
    }

    inner class StandingsListPresenter: IStandingsListPresenter {
        val teams = mutableListOf<Team>()
        val standingsList = mutableListOf<Standings>()
        override var itemClickListener: ((StandingsItemView) -> Unit)? = null

        override fun bindView(view: StandingsItemView) {
            val standings = standingsList[view.pos]
            val team = teams.first { it.id == standings.teamId }
            with (view) {
                with(team) {
                    setTeam("$market $name")
                    loadLogo(logoUrl + alias)
                }
                with (standings) {
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
        loadTeamsData()

        weeksListPresenter.itemClickListener = {
            val week = weeksListPresenter.weeks[it.pos]
            router.navigateTo(screens.games(season, week))
        }
    }

    private fun loadTeamsData() {
        teamsRepo.getTeams()
            .observeOn(uiScheduler)
            .subscribe({
                standingsListPresenter.teams.clear()
                standingsListPresenter.teams.addAll(it)
                viewState.updateList()
            }, {
                println(it.message)
            })
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
                standingsListPresenter.standingsList.addAll(it.sortedBy { standings ->
                    standings.wins - standings.losses}.asReversed())
                viewState.updateList()
            },{
                println(it.message)
            })
    }



    fun backPressed(): Boolean {
        router.navigateTo(screens.seasons())
        return true
    }
//    fun backPressed(): Boolean {
//        router.exit()
//        return true
//    }
}