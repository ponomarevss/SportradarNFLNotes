package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemDivisionBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.image.IImageLoader

class DivisionsRVAdapter(private val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<DivisionsRVAdapter.ViewHolder>() {

    private var standingsList: List<Standings> = mutableListOf()
    private var divisions: List<String> = mutableListOf()

    fun setData(data: List<Standings>) {
        this.standingsList = data
        divisions = data.map { it.team.division }.toSet().toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemDivisionBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            StandingsRVAdapter(imageLoader)
        )

    override fun getItemCount() = divisions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(divisions[position])
    }

    inner class ViewHolder(
        private val vb: ItemDivisionBinding,
        private val context: Context,
        private val standingsAdapter: StandingsRVAdapter
    ) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(divisionName: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvTitle.text = divisionName
                vb.rvStandings.layoutManager = LinearLayoutManager(context)
                standingsAdapter.setData(
                    standingsList
                        .filter { it.team.division == divisionName }
                        .sortedBy { it.wins - it.losses }
                        .reversed()
                )
                vb.rvStandings.adapter = standingsAdapter
            }
        }
    }
}