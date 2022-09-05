package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemSeasonBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.ISeasonsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.SeasonItemView

class SeasonsRVAdapter(private var onListItemClickListener: OnListItemClickListener) : RecyclerView.Adapter<SeasonsRVAdapter.ViewHolder>() {

    private var data: List<Season> = mutableListOf()

    fun setData(data: List<Season>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(private val vb: ItemSeasonBinding) : RecyclerView.ViewHolder(vb.root) {

        fun bind(data: Season) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvYear.text = data.year.toString()
                vb.tvStatus.text = data.status
                vb.tvType.text = data.type
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: Season) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Season)
    }

}