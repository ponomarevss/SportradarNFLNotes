package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.ponomarevss.sportradarnflnotes.R
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.StandingsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.utils.OnlineLiveData
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel.SeasonViewModel

class SeasonFragment : MvpAppCompatFragment() {
    companion object {
        private const val SEASON_ARG = "season"
        private const val WEEKS_SPAN_COUNT = 6
        private const val STANDINGS_SPAN_COUNT = 2


        fun newInstance(season: Season) = SeasonFragment().apply {
            arguments = Bundle().apply { putParcelable(SEASON_ARG, season) }
        }
    }

    private val router: Router by inject()
    private val screens: IScreens by inject()

    private val seasonViewModel: SeasonViewModel by viewModel {
        parametersOf(arguments?.getParcelable(SEASON_ARG)!!)
    }

    private var vb: FragmentSeasonBinding? = null

    private var weeksAdapter: WeeksRVAdapter? = null
    private var standingsAdapter: StandingsRVAdapter? = null

    private val onListItemClickListener: WeeksRVAdapter.OnListItemClickListener =
        object : WeeksRVAdapter.OnListItemClickListener {
            override fun onItemClick(seasonId: String, weekId: String) {
                router.navigateTo(screens.games(seasonId, weekId))
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
        fetchData()
        initView()
    }

    private fun initView() {
        vb?.rvWeeks?.layoutManager = GridLayoutManager(context, WEEKS_SPAN_COUNT)
        vb?.rvWeeks?.adapter = weeksAdapter

        vb?.tvUpdateLabel?.text = resources.getString(R.string.last_update)

        vb?.rvStandings?.layoutManager = GridLayoutManager(context, STANDINGS_SPAN_COUNT)
        vb?.rvStandings?.adapter = standingsAdapter
    }

    private fun fetchData() {
        seasonViewModel.loadInitData()
        OnlineLiveData(requireContext()).observe(
            viewLifecycleOwner,
            seasonViewModel.onlineLiveDataObserver()
        )
    }

    private fun initViewModel() {
        val season: Season? = arguments?.getParcelable(SEASON_ARG)
        if (season != null) {
            weeksAdapter = WeeksRVAdapter(season.id, onListItemClickListener)
        }
        with(seasonViewModel.weeksLiveData) {
            observe(viewLifecycleOwner) { value?.let { weeksAdapter?.setData(it) } }
        }

        with(seasonViewModel.timestampLiveData) {
            observe(viewLifecycleOwner) { value?.let { vb?.tvUpdateValue?.text = it.toString() }}
        }

        standingsAdapter = StandingsRVAdapter()
        with(seasonViewModel.standingsLiveData) {
            observe(viewLifecycleOwner) { value?.let { standingsAdapter?.setData(it) } }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}