package ru.geekbrains.ponomarevss.sportradarnflnotes.mvvm.model.entity.room

import androidx.room.Entity

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