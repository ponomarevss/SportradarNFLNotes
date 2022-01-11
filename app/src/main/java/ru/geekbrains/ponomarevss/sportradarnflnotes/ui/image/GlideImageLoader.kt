package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.network.INetworkStatus

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        GlideApp.with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }
}