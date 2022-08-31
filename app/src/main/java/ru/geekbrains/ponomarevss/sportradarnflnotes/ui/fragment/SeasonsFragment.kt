package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

class SeasonsFragment : MvpAppCompatFragment(), BackButtonListener {
    companion object {
        fun newInstance() = SeasonsFragment()
    }

    private var vb: FragmentSeasonsBinding? = null
    private val seasonsViewModel: SeasonsViewModel by viewModel()

    var adapter: SeasonsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        seasonsViewModel.liveDataForViewToObserve.observe(viewLifecycleOwner) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}