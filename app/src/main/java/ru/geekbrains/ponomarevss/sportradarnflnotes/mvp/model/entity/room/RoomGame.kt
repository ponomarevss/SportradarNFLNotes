package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RoomGame(
    @PrimaryKey var id: String,
    var status: String,
    var scheduled: String,
    var homeId: String,
    var awayId: String,
    var weekId: String,
    var isWatched: Boolean
)