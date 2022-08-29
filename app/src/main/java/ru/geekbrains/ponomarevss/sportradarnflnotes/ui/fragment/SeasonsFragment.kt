package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.appstate.SeasonsAppState
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.SeasonsPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.SeasonsView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter

class SeasonsFragment: MvpAppCompatFragment(), SeasonsView, BackButtonListener {
    companion object {
        fun newInstance() = SeasonsFragment()
    }

//    val presenter: SeasonsPresenter by moxyPresenter {
//        SeasonsPresenter(AndroidSchedulers.mainThread()).apply {
//            App.instance.appComponent.inject(this)
//        }
//    }

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

    override fun init() {
        vb?.rvSeasons?.layoutManager = LinearLayoutManager(context)
        adapter = SeasonsRVAdapter(presenter.seasonsListPresenter)
        vb?.rvSeasons?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()



    override fun renderData(appState: SeasonsAppState) {
        when (appState) {
            is SeasonsAppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
//                        getString(R.string.dialog_title_sorry),
//                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is SeasonsAppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
//                    vb.progressBarHorizontal.visibility = VISIBLE
//                    vb.progressBarRound.visibility = GONE
//                    vb.progressBarHorizontal.progress = appState.progress
                } else {
//                    vb.progressBarHorizontal.visibility = GONE
//                    vb.progressBarRound.visibility = VISIBLE
                }
            }
            is SeasonsAppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }
}