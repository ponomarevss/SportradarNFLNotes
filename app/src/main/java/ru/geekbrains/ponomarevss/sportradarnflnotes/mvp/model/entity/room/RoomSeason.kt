package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomSeason(
    @PrimaryKey var id: String,
    var year: Int,
    var type: String,
    var name: String
)