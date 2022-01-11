package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentGamesBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentSeasonsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentTableBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentWeeksBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.GamesPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.SeasonsPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.TablePresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.WeeksPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GamesView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.SeasonsView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.TableView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.WeeksView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.GamesRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.SeasonsRVAdapter
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.WeeksRVAdapter

class TableFragment: MvpAppCompatFragment(), TableView, BackButtonListener {
    companion object {
        fun newInstance() = TableFragment()
    }

    val presenter: TablePresenter by moxyPresenter {
        TablePresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentTableBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentTableBinding.inflate(inflater, container, false).also {
                vb = it
            }
            .root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {

    }

    override fun updateList() {

    }

    override fun backPressed() = presenter.backPressed()
}