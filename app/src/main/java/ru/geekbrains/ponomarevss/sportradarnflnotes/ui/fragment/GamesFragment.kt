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
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.GamesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.viewmodel.GamesViewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image.GlideImageLoader

class GamesFragment : MvpAppCompatFragment() {
    companion object {
        private const val SEASON_AND_WEEK_ARGS = "seasonId weekId"

        fun newInstance(seasonId: String, weekId: String) = GamesFragment().apply {
            arguments = Bundle().apply {
                putStringArray(SEASON_AND_WEEK_ARGS, arrayOf(seasonId, weekId))
            }
        }
    }

    val screens: IScreens by inject()

    private val gamesViewModel: GamesViewModel by viewModel {
        parametersOf(arguments?.getStringArray(SEASON_AND_WEEK_ARGS)!!)
    }

    private var vb: FragmentGamesBinding? = null

    var adapter: GamesRVAdapter? = null

    private val onListItemClickListener: GamesRVAdapter.OnListItemClickListener =
        object : GamesRVAdapter.OnListItemClickListener {
            override fun onItemClick(game: Game, weekId: String) {
                gamesViewModel.itemClicked(game, weekId)
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
        vb?.rvGames?.layoutManager = LinearLayoutManager(context)
        vb?.rvGames?.adapter = adapter
    }

    private fun initViewModel() {
        val weekId: String = arguments?.getStringArray(SEASON_AND_WEEK_ARGS)!![1]
        adapter = GamesRVAdapter(weekId, onListItemClickListener, GlideImageLoader())
        with(gamesViewModel.liveData) {
            observe(viewLifecycleOwner) { value?.let { adapter?.setData(it) } }
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