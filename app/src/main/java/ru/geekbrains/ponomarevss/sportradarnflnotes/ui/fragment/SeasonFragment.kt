package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.StandingsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

@RequiresApi(33)
class SeasonFragment : MvpAppCompatFragment(), WeeksView {
    companion object {
        private const val SEASON_ARG = "season"
        private const val SPAN_COUNT = 6

        fun newInstance(season: Season) = SeasonFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SEASON_ARG, season)
            }
        }
    }

    private val season = arguments?.getParcelable(SEASON_ARG, Season::class.java) as Season
    private val seasonViewModel: SeasonViewModel by viewModel { parametersOf(season) }

    private var weeksAdapter: WeeksRVAdapter? = null
    private var standingsAdapter: StandingsRVAdapter? = null
    private var vb: FragmentSeasonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvWeeks?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        weeksAdapter = WeeksRVAdapter(presenter.weeksListPresenter)
        vb?.rvWeeks?.adapter = weeksAdapter

        vb?.rvStandings?.layoutManager = LinearLayoutManager(context)
        standingsAdapter = StandingsRVAdapter(presenter.standingsListPresenter, GlideImageLoader())
        vb?.rvStandings?.adapter = standingsAdapter
    }

    override fun updateList() {
//        weeksAdapter?.notifyDataSetChanged()
//        standingsAdapter?.notifyDataSetChanged()
    }

//    override fun backPressed() = presenter.backPressed()
}