package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}