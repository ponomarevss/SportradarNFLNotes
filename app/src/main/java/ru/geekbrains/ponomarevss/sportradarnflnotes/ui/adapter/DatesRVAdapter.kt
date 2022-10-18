package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemDateBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Game
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.image.IImageLoader

class DatesRVAdapter(
    private val weekId: String,
    private var onListItemClickListener: GamesRVAdapter.OnListItemClickListener,
    private var onRateChangeListener: GamesRVAdapter.OnRateChangeListener,
    private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<DatesRVAdapter.ViewHolder>() {

    private var gamesList: List<Game> = mutableListOf()
    private var dates: List<String> = mutableListOf()

    fun setData(data: List<Game>) {
        this.gamesList = data
        dates = data.map { it.scheduled.substring(0..9) }.toSet().toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemDateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            GamesRVAdapter(weekId, onListItemClickListener, onRateChangeListener, imageLoader)
        )

    override fun getItemCount() = dates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dates[position])
    }

    inner class ViewHolder(
        private val vb: ItemDateBinding,
        private val context: Context,
        private val gamesAdapter: GamesRVAdapter
    ) : RecyclerView.ViewHolder(vb.root) {

        fun bind(data: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvDate.text = data
                vb.rvGames.layoutManager = LinearLayoutManager(context)
                gamesAdapter.setData(
                    gamesList.filter { it.scheduled.substring(0..9) == data }
                )
                vb.rvGames.adapter = gamesAdapter
            }
        }
    }
}