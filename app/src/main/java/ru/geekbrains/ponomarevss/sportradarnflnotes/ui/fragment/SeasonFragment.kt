package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.SeasonPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.StandingsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image.GlideImageLoader

class SeasonFragment: MvpAppCompatFragment(), WeeksView, BackButtonListener {
    companion object {
        private const val SEASON_ARG = "season"
        private const val SPAN_COUNT = 6

        fun newInstance(season: Season) = SeasonFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SEASON_ARG, season)
            }
        }
    }

    val presenter: SeasonPresenter by moxyPresenter {
        val season = arguments?.getParcelable<Season>(SEASON_ARG) as Season
        SeasonPresenter(AndroidSchedulers.mainThread(), season).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var weeksAdapter: WeeksRVAdapter? = null
    var standingsAdapter: StandingsRVAdapter? = null
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
        weeksAdapter?.notifyDataSetChanged()
        standingsAdapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}