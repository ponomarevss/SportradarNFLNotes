package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemStandingsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.general.Standings

class StandingsRVAdapter(/*val imageLoader: IImageLoader<ImageView>*/) :
    RecyclerView.Adapter<StandingsRVAdapter.ViewHolder>() {

    private var data: List<Standings> = mutableListOf()

    fun setData(data: List<Standings>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemStandingsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(private val vb: ItemStandingsBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(data: Standings) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                vb.tvTeam.text = data.team.name
                vb.tvW.text = data.wins.toString()
                vb.tvT.text = data.ties.toString()
                vb.tvL.text = data.losses.toString()
                vb.tvDivW.text = data.divWins.toString()
                vb.tvDivT.text = data.divTies.toString()
                vb.tvDivL.text = data.divLosses.toString()
            }
        }

//        fun loadLogo(url: String) = with(vb) {
//            imageLoader.loadInto(url, ivTeam)
//        }
    }
}