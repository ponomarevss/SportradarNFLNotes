package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemPeriodBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemWeekBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IPeriodsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IWeeksListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.PeriodItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.WeekItemView

class PeriodsRVAdapter(val presenter: IPeriodsListPresenter) : RecyclerView.Adapter<PeriodsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPeriodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                itemView.setOnClickListener {
                    presenter.itemClickListener?.invoke(this)
                }
            }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemPeriodBinding) : RecyclerView.ViewHolder(vb.root), PeriodItemView {
        override var pos = -1

        override fun setPeriodType(text: String) {
            vb.tvPeriodType.text = text
        }

        override fun setNumber(text: String) {
            vb.tvNumber.text = text
        }

        override fun setHomePoints(text: String) {
            vb.tvHomePoints.text = text
        }

        override fun setAwayPoints(text: String) {
            vb.tvAwayPoints.text = text
        }
    }
}