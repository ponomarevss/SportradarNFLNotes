package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["id", "gameId"],
    foreignKeys = [ForeignKey(
        entity = RoomGame::class,
        parentColumns = ["id"],
        childColumns = ["gameId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomRival(
    var id: String,
    var status: String,
    var name: String,
    var alias: String,
    var gameNumber: Int,
    var gameId: String
)