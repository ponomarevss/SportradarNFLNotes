package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["seasonId", "teamId"]
)
data class RoomStandings(
    var seasonId: String,
    var teamId: String,
    var wins: Int,
    var losses: Int,
    var ties: Int,
    var divWins: Int,
    var divLosses: Int,
    var divTies: Int
)