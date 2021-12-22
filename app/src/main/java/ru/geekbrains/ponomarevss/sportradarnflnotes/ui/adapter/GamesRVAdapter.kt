package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemWeekBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView

class GamesRVAdapter(val presenter: IGamesListPresenter) :
    RecyclerView.Adapter<GamesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    inner class ViewHolder(val vb: ItemGameBinding) : RecyclerView.ViewHolder(vb.root),
        GameItemView {
        override var pos = -1

        override fun setStatus(text: String) {
            vb.tvStatus.text = text
        }

        override fun setScheduled(text: String) {
            vb.tvScheduled.text = text
        }

        override fun setHome(text: String) {
            vb.tvHome.text = text
        }

        override fun setAway(text: String) {
            vb.tvAway.text = text
        }

        override fun setScoring(text: String) {
            vb.tvScoring.text = text
        }
    }
}