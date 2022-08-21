package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room.old

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomConference::class,
        parentColumns = ["id"],
        childColumns = ["conferenceId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomDivision(
    @PrimaryKey var id: String,
    var name: String,
    var alias: String,
    var conferenceId: String
)