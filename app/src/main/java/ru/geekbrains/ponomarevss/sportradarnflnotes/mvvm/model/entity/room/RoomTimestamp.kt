package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomTimestamp(
    @PrimaryKey var name: String,
    var timestamp: Long,
)