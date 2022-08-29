package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView

class SeasonsRVAdapter(val presenter: ISeasonsListPresenter) :
    RecyclerView.Adapter<SeasonsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemSeasonBinding.inflate(
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemSeasonBinding) : RecyclerView.ViewHolder(vb.root),
        SeasonItemView {

        override var pos = -1

        override fun setYear(text: String) {
            vb.tvYear.text = text
        }

        override fun setStatus(text: String) {
            vb.tvStatus.text = text
        }

        override fun setType(text: String) {
            vb.tvType.text = text
        }
    }
}