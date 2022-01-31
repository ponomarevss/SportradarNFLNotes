package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.image

import android.widget.ImageView
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image.IImageLoader

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        GlideApp.with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }
}