package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.StandingsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.utils.OnlineLiveData
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonViewModel

class SeasonFragment : MvpAppCompatFragment() {
    companion object {
        private const val SEASON_ARG = "season"
        private const val SPAN_COUNT = 6

        fun newInstance(season: Season) = SeasonFragment().apply {
            arguments = Bundle().apply { putParcelable(SEASON_ARG, season) }
        }
    }

    private val router: Router by inject()
    private val screens: IScreens by inject()

//    private val season: Season = arguments?.getParcelable(SEASON_ARG)!!
//    private val seasonViewModel: SeasonViewModel by viewModel { parametersOf(season) }
    private val seasonViewModel: SeasonViewModel by viewModel { parametersOf(arguments?.getParcelable(SEASON_ARG)!!) }

    private var vb: FragmentSeasonBinding? = null

    private var weeksAdapter: WeeksRVAdapter? = null
    private var standingsAdapter: StandingsRVAdapter? = null

    private val onListItemClickListener: WeeksRVAdapter.OnListItemClickListener =
        object : WeeksRVAdapter.OnListItemClickListener {
            override fun onItemClick(sData: Season, wData: Week) {
                router.navigateTo(screens.games(sData, wData))
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        fetchWeeks()
        initView()
    }

    private fun initView() {
        vb?.rvWeeks?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        vb?.rvWeeks?.adapter = weeksAdapter

//        vb?.rvStandings?.layoutManager = LinearLayoutManager(context)
//        standingsAdapter = StandingsRVAdapter(presenter.standingsListPresenter, GlideImageLoader())
//        vb?.rvStandings?.adapter = standingsAdapter
    }

    private fun fetchWeeks() {
        seasonViewModel.loadInitWeeks()
        OnlineLiveData(requireContext()).observe(
            viewLifecycleOwner,
            seasonViewModel.onlineLiveDataObserver()
        )
    }

    private fun initViewModel() {
        val season: Season = arguments?.getParcelable(SEASON_ARG)!!
        weeksAdapter = WeeksRVAdapter(season, onListItemClickListener)
        with(seasonViewModel.weeksLiveData) {
            observe(viewLifecycleOwner) { value?.let { weeksAdapter?.setData(it) } }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}