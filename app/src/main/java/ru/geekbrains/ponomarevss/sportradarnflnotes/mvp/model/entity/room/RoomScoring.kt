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
data class RoomScoring(
    @PrimaryKey var id: String, // "gameId + 'scoring'"
    var homePoints: Int,
    var awayPoints: Int,
    var gameId: String
)