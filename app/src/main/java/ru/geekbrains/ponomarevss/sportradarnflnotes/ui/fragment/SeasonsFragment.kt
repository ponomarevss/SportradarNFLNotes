package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

class SeasonsFragment : MvpAppCompatFragment(), BackButtonListener {
    companion object {
        fun newInstance() = SeasonsFragment()
    }

    private var vb: FragmentSeasonsBinding? = null
//    private val viewModel: SeasonsViewModel by inject()
    private val viewModel: SeasonsViewModel by viewModel()
    private val observer = Observer<List<Season>> { init(it) }
    var adapter: SeasonsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root.also { Log.e("AAA", "fragment created") }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveDataForViewToObserve.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
        Log.e("AAA", "fragment destroyed")
    }

    private fun init(seasons: List<Season>) {
        vb?.rvSeasons?.layoutManager = LinearLayoutManager(context)
        adapter = SeasonsRVAdapter(seasons)
//        vb?.rvSeasons?.adapter = adapter
    }

//    override fun updateList() {
//        adapter?.notifyDataSetChanged()
//    }

//    override fun backPressed() = presenter.backPressed()


}