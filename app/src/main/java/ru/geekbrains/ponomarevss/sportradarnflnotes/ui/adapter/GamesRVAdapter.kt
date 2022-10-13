package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.NAME_LOGO_URL
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.image.IImageLoader
import java.time.OffsetDateTime
import java.util.*

class GamesRVAdapter(
    private val weekId: String,
    private var onListItemClickListener: OnListItemClickListener,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<GamesRVAdapter.ViewHolder>() {

    private var data: List<Game> = mutableListOf()

    fun setData(data: List<Game>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGameBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], weekId, position)
    }

    inner class ViewHolder(private val vb: ItemGameBinding) : RecyclerView.ViewHolder(vb.root) {

        fun bind(data: Game, weekId: String, position: Int) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvStatus.text = data.status
                vb.tvScheduled.text = formattedDate(data.scheduled)
                vb.tvHome.text = data.home.alias
                vb.tvAway.text = data.away.alias
                vb.tvHomeScoring.text = if (data.isWatched) data.homePoints.toString() else ""
                vb.tvAwayScoring.text = if (data.isWatched) data.awayPoints.toString() else ""
                itemView.setOnClickListener {
                    setGameWatched(data, weekId)
                    notifyItemChanged(position)
                }
                loadHomeLogo(NAME_LOGO_URL + data.home.alias)
                loadAwayLogo(NAME_LOGO_URL + data.away.alias)
            }
        }

        private fun loadHomeLogo(url: String) = with(vb) { imageLoader.loadInto(url, ivHome) }

        private fun loadAwayLogo(url: String) = with(vb) { imageLoader.loadInto(url, ivAway) }

        private fun formattedDate(timeString: String): String {
            return if (Build.VERSION.SDK_INT >= 26) {
                Date(OffsetDateTime.parse(timeString).toEpochSecond() * 1000).toString()
            } else {
                timeString
            }
        }
    }

    private fun setGameWatched(game: Game, weekId: String) {
        onListItemClickListener.onItemClick(game, weekId)
    }

    interface OnListItemClickListener {
        fun onItemClick(game: Game, weekId: String)
    }
}