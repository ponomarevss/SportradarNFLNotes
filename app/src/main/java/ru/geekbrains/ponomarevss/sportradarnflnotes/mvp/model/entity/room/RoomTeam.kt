package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomTeam(
    @PrimaryKey var id: String,
    var name: String,
    var market: String,
    var alias: String,
    var division: String,
//    var conference: String
)