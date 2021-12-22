package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentWeeksBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.SeasonsPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.WeeksPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.SeasonsView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter

class WeeksFragment: MvpAppCompatFragment(), WeeksView, BackButtonListener {
    companion object {
        private const val SEASON_ARG = "season"

        fun newInstance(season: Season) = WeeksFragment().apply {
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
    private var vb: FragmentWeeksBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeeksBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvWeeks?.layoutManager = LinearLayoutManager(context)
        adapter = WeeksRVAdapter(presenter.weeksListPresenter)
        vb?.rvWeeks?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}