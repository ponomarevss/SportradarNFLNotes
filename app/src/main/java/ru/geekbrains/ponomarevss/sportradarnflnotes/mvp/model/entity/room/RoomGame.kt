package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Rival
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Scoring

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomWeek::class,
        parentColumns = ["id"],
        childColumns = ["weekId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGame(
    @PrimaryKey var id: String,
    var status: String,
    var scheduled: String,
    var weekId: String
)