package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemStandingsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Standings
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.general.Week
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IStandingsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.StandingsItemView

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
                vb.tvTeam.text = data.team.alias
            }
        }

//        fun loadLogo(url: String) = with(vb) {
//            imageLoader.loadInto(url, ivTeam)
//        }
    }
}