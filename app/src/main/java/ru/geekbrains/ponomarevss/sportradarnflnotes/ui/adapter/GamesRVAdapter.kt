package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Game

class GamesRVAdapter(
    private val weekId: String,
    private var onListItemClickListener: OnListItemClickListener/*,
    val imageLoader: IImageLoader<ImageView>*/
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
                vb.tvScheduled.text = data.scheduled
                vb.tvHome.text = data.home.alias
                vb.tvAway.text = data.away.alias
                vb.tvHomeScoring.text = if (data.isWatched) data.homePoints.toString() else ""
                vb.tvAwayScoring.text = if (data.isWatched) data.awayPoints.toString() else ""
                itemView.setOnClickListener {
                    setGameWatched(data, weekId)
                    notifyItemChanged(position)
                }
            }
        }

        private fun loadHomeLogo(url: String) = with(vb) {
//            imageLoader.loadInto(url, ivHome)
        }

        fun loadAwayLogo(url: String) = with(vb) {
//            imageLoader.loadInto(url, ivAway)
        }
    }

    private fun setGameWatched(game: Game, weekId: String) {
        onListItemClickListener.onItemClick(game, weekId)
    }

    interface OnListItemClickListener {
            fun onItemClick(game: Game, weekId: String)
    }
}