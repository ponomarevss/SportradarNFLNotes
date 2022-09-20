package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentGamesBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.GamesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.GamesViewModel

class GamesFragment : MvpAppCompatFragment() {
    companion object {
        private const val SEASON_ARG = "season"
        private const val WEEK_ARG = "week"

        fun newInstance(season: Season, week: Week) = GamesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SEASON_ARG, season)
                putParcelable(WEEK_ARG, week)
            }
        }
    }

    val screens: IScreens by inject()

    private val gamesViewModel: GamesViewModel by viewModel {
        parametersOf(
            arguments?.getParcelable(SEASON_ARG)!!,
            arguments?.getParcelable(WEEK_ARG)!!
        )
    }

    private var vb: FragmentGamesBinding? = null

    var adapter: GamesRVAdapter? = null

    private val onListItemClickListener: GamesRVAdapter.OnListItemClickListener =
        object : GamesRVAdapter.OnListItemClickListener {
            override fun onItemClick(week: Week, game: Game) {}
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGamesBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    fun initView() {
        vb?.rvGames?.layoutManager = LinearLayoutManager(context)
        vb?.rvGames?.adapter = adapter
    }

    fun initViewModel() {
        val season: Season = arguments?.getParcelable(SEASON_ARG)!!
        val week: Week = arguments?.getParcelable(WEEK_ARG)!!
        adapter = GamesRVAdapter(week, onListItemClickListener)
//        adapter = GamesRVAdapter(presenter.gamesListPresenter, GlideImageLoader())
        with(gamesViewModel.liveData) {
            observe(viewLifecycleOwner) { value?.let { adapter?.setData(it) } }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}