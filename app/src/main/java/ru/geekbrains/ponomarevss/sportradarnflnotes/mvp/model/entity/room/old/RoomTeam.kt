package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomDivision::class,
        parentColumns = ["id"],
        childColumns = ["divisionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomTeam(
    @PrimaryKey var id: String,
    var name: String,
    var market: String,
    var alias: String,
    var divisionId: String
)