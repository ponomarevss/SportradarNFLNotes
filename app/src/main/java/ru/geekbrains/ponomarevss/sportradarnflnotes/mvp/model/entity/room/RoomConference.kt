package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomLeague::class,
        parentColumns = ["id"],
        childColumns = ["leagueId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomConference(
    @PrimaryKey var id: String,
    var name: String,
    var alias: String,
    var leagueId: String
)