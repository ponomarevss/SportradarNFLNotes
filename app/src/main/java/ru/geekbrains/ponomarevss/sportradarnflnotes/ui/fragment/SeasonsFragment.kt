package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.appstate.SeasonsAppState
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.SeasonsPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.SeasonsView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.viewmodel.SeasonsViewModel

class SeasonsFragment: MvpAppCompatFragment(), SeasonsView, BackButtonListener {
    companion object {
        fun newInstance() = SeasonsFragment()
    }

//    val presenter: SeasonsPresenter by moxyPresenter {
//        SeasonsPresenter(AndroidSchedulers.mainThread()).apply {
//            App.instance.appComponent.inject(this)
//        }
//    }

    val viewModel: SeasonsViewModel by viewModel()

    var adapter: SeasonsRVAdapter? = null
    private var vb: FragmentSeasonsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

//    override fun init() {
//        vb?.rvSeasons?.layoutManager = LinearLayoutManager(context)
//        adapter = SeasonsRVAdapter(presenter.seasonsListPresenter)
//        vb?.rvSeasons?.adapter = adapter
//    }

//    override fun updateList() {
//        adapter?.notifyDataSetChanged()
//    }

//    override fun backPressed() = presenter.backPressed()


}