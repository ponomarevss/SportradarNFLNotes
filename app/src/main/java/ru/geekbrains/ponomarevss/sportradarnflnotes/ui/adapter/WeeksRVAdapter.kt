package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemWeekBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Season
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week

class WeeksRVAdapter(
    private var season: Season,
    private var onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<WeeksRVAdapter.ViewHolder>() {

    private var data: List<Week> = mutableListOf()

    fun setData(data: List<Week>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemWeekBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(season, data[position])
    }

    inner class ViewHolder(private val vb: ItemWeekBinding) : RecyclerView.ViewHolder(vb.root) {

        fun bind(season: Season, data: Week, ) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvTitle.text = data.title
                itemView.setOnClickListener { openInNewWindow(season, data) }
            }
        }
    }

    private fun openInNewWindow(season: Season, listItemData: Week) {
        onListItemClickListener.onItemClick(season, listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(sData: Season, wData: Week)
    }
}