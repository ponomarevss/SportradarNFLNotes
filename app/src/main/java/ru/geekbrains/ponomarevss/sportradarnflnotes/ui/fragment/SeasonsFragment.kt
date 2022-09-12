package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.navigation.IScreens
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.utils.OnlineLiveData
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

class SeasonsFragment : MvpAppCompatFragment() {
    companion object {
        private const val SPAN_COUNT = 2
        fun newInstance() = SeasonsFragment()
    }

    private var isNetworkAvailable: Boolean = false

    private val router: Router by inject()
    private val screens: IScreens by inject()

    private var vb: FragmentSeasonsBinding? = null
    private val seasonsViewModel: SeasonsViewModel by viewModel()
    private var adapter: SeasonsRVAdapter? = null

    private val onListItemClickListener: SeasonsRVAdapter.OnListItemClickListener =
        object : SeasonsRVAdapter.OnListItemClickListener {
            override fun onItemClick(data: Season) {
                router.navigateTo(screens.season(data))
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentSeasonsBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToNetworkState()
        initViewModel()
    }

    private fun initViewModel() {
        seasonsViewModel.liveData.observe(viewLifecycleOwner) {
            initView()
        }
        seasonsViewModel.getData(isNetworkAvailable)
    }

    private fun initView() {
        vb?.rvSeasons?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        adapter = SeasonsRVAdapter(onListItemClickListener)
        vb?.rvSeasons?.adapter = adapter
        seasonsViewModel.liveData.value?.let { adapter?.setData(it) }
    }

    private fun subscribeToNetworkState() {
        val onlineLiveData = OnlineLiveData(requireContext())
        onlineLiveData.observe(viewLifecycleOwner) {
            isNetworkAvailable = it
            seasonsViewModel.getData(isNetworkAvailable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}