package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomScoring::class,
        parentColumns = ["id"],
        childColumns = ["scoringId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomPeriod(
    @PrimaryKey var id: String,
    var periodType: String,
    var number: Int,
    var homePoints: Int,
    var awayPoints: Int,
    var scoringId: String
)