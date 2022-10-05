package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}