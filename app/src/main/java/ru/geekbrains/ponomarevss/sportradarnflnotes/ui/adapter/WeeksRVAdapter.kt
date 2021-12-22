package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemWeekBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView

class WeeksRVAdapter(val presenter: IWeeksListPresenter) :
    RecyclerView.Adapter<WeeksRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemWeekBinding.inflate(
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

    inner class ViewHolder(val vb: ItemWeekBinding) : RecyclerView.ViewHolder(vb.root),
        WeekItemView {

        override fun setTitle(text: String) {
            vb.tvTitle.text = text
        }

        override var pos = -1
    }
}