package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomLeague(
    @PrimaryKey var id: String,
    var name: String,
    var alias: String
)