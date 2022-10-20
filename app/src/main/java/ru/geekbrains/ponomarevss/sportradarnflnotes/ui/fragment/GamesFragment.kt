package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentGamesBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel.GamesViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.DatesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.GamesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image.GlideImageLoader

class GamesFragment : Fragment() {
    companion object {
        private const val SEASON_AND_WEEK_ARGS = "seasonId weekId"

        fun newInstance(seasonId: String, weekId: String) = GamesFragment().apply {
            arguments = Bundle().apply {
                putStringArray(SEASON_AND_WEEK_ARGS, arrayOf(seasonId, weekId))
            }
        }
    }

    private val gamesViewModel: GamesViewModel by viewModel {
        parametersOf(arguments?.getStringArray(SEASON_AND_WEEK_ARGS)!!)
    }

    private var vb: FragmentGamesBinding? = null

    private var datesAdapter: DatesRVAdapter? = null

    private val onListItemClickListener: GamesRVAdapter.OnListItemClickListener =
        object : GamesRVAdapter.OnListItemClickListener {
            override fun onItemClick(game: Game, weekId: String) {
                gamesViewModel.itemClicked(game, weekId)
            }
        }

    private val onRateChangeListener: GamesRVAdapter.OnRateChangeListener =
        object : GamesRVAdapter.OnRateChangeListener {
            override fun onBarRated(game: Game, weekId: String, rating: Float) {
                gamesViewModel.rateBarSwiped(game, weekId, rating)
            }
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
        loadData()
        initView()
    }

    private fun initView() {
        vb?.rvDates?.layoutManager = LinearLayoutManager(context)
        vb?.rvDates?.adapter = datesAdapter
    }

    private fun initViewModel() {
        val weekId: String = arguments?.getStringArray(SEASON_AND_WEEK_ARGS)!![1]
        datesAdapter = DatesRVAdapter(
            weekId,
            onListItemClickListener,
            onRateChangeListener,
            GlideImageLoader()
        )
        with(gamesViewModel.liveData) {
            observe(viewLifecycleOwner) { value?.let { datesAdapter?.setData(it) } }
        }
    }

    private fun loadData() {
        gamesViewModel.initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}