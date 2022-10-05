package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomWeek(
    @PrimaryKey var id: String,
    var sequence: Int,
    var title: String,
    var seasonId: String
)