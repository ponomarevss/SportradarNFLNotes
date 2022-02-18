package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.ponomarevss.sportradarnflnotes.databinding.ItemGameBinding
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.presenter.list.IGamesListPresenter
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.view.list.GameItemView

class GamesRVAdapter(val presenter: IGamesListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<GamesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGameBinding.inflate(
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    inner class ViewHolder(val vb: ItemGameBinding) : RecyclerView.ViewHolder(vb.root),
        GameItemView {
        override var pos = -1

        override fun setStatus(text: String) = with(vb) {
            tvStatus.text = text
        }

        override fun setScheduled(text: String) = with(vb) {
            tvScheduled.text = text
        }

        override fun setHome(text: String) = with(vb) {
            tvHome.text = text
        }

        override fun setAway(text: String) = with(vb) {
            tvAway.text = text
        }

        override fun setScoring(text: String) = with(vb) {
            tvScoring.text = text
        }

        override fun loadHomeLogo(url: String) = with(vb) {
            imageLoader.loadInto(url, ivHome)
        }

        override fun loadAwayLogo(url: String) = with(vb) {
            imageLoader.loadInto(url, ivAway)
        }
    }
}