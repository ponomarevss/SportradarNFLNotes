package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomSeason(
    @PrimaryKey var id: String,
    var year: Int,
    var status: String,
    var type: String
)