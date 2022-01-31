package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.common.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.WeeksPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter

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

    val presenter: WeeksPresenter by moxyPresenter {
        val season = arguments?.getParcelable<Season>(SEASON_ARG) as Season
        WeeksPresenter(AndroidSchedulers.mainThread(), season).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: WeeksRVAdapter? = null
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
        adapter = WeeksRVAdapter(presenter.weeksListPresenter)
        vb?.rvWeeks?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}