package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemStandingsBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IStandingsListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.StandingsItemView

class StandingsRVAdapter(val presenter: IStandingsListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<StandingsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemStandingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemStandingsBinding) : RecyclerView.ViewHolder(vb.root), StandingsItemView {

        override var pos = -1

        override fun setTeam(text: String) = with(vb) {
            tvTeam.text = text
        }

        override fun setWLT(text: String) = with(vb) {
            tvWLT.text = text
        }

        override fun setDivWLT(text: String) = with(vb) {
            tvDivWLT.text = text
        }

        override fun loadLogo(url: String) = with(vb) {
            imageLoader.loadInto(url, ivTeam)
        }
    }
}