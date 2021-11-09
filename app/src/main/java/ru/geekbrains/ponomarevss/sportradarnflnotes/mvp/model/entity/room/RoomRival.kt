package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Rival
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Scoring

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGame::class,
        parentColumns = ["id"],
        childColumns = ["gameId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomRival(
    @PrimaryKey var id: String,
    var status: String,
    var name: String,
    var alias: String,
    var gameNumber: Int,
    var gameId: String
)