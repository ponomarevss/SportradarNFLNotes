package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGame(
    @PrimaryKey var id: String,
    var status: String,
    var scheduled: String,
    var homeId: String,
    var awayId: String,
    var homePoints: Int,
    var awayPoints: Int,
    var weekId: String,
    var isWatched: Boolean
)