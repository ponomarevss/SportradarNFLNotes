package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.FragmentGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.oldcommon.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.GamePresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.GameView
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.App
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.BackButtonListener
import ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter.PeriodsRVAdapter

class GameFragment: MvpAppCompatFragment(), GameView, BackButtonListener {
    companion object {
        private const val GAME_ARG = "game"

        fun newInstance(game: Game) = GameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(GAME_ARG, game)
            }
        }
    }

    val presenter: GamePresenter by moxyPresenter {
        val game = arguments?.getParcelable<Game>(GAME_ARG) as Game
        GamePresenter(AndroidSchedulers.mainThread(), game).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: PeriodsRVAdapter? = null
    private var vb: FragmentGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGameBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvPeriods?.layoutManager = LinearLayoutManager(context)
        adapter = PeriodsRVAdapter(presenter.periodsListPresenter)
        vb?.rvPeriods?.adapter = adapter
    }

    override fun setScheduled(text: String) {
        vb?.tvScheduled?.text = text
    }

    override fun setStatus(text: String) {
        vb?.tvStatus?.text = text
    }

    override fun setHomeName(text: String) {
        vb?.tvHomeName?.text = text
    }

    override fun setHomeMarket(text: String) {
        vb?.tvHomeMarket?.text = text
    }

    override fun setHomePoints(text: String) {
        vb?.tvHomePoints?.text = text
    }

    override fun setAwayName(text: String) {
        vb?.tvAwayName?.text = text
    }

    override fun setAwayMarket(text: String) {
        vb?.tvAwayMarket?.text = text
    }

    override fun setAwayPoints(text: String) {
        vb?.tvAwayPoints?.text = text
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}