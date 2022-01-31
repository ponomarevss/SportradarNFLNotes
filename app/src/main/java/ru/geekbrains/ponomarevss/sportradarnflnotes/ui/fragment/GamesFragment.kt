package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentGamesBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.GamesPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GamesView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.GamesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image.GlideImageLoader

class GamesFragment: MvpAppCompatFragment(), GamesView, BackButtonListener {
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

    val presenter: GamesPresenter by moxyPresenter {
        val season = arguments?.getParcelable<Season>(SEASON_ARG) as Season
        val week = arguments?.getParcelable<Week>(WEEK_ARG) as Week
        GamesPresenter(AndroidSchedulers.mainThread(), season, week).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: GamesRVAdapter? = null
    private var vb: FragmentGamesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGamesBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvGames?.layoutManager = LinearLayoutManager(context)
        adapter = GamesRVAdapter(presenter.gamesListPresenter, GlideImageLoader())
        vb?.rvGames?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}