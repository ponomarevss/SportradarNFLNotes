package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.Type

@Entity
data class RoomSeason(
    @PrimaryKey var id: String,
    var year: Int,
    var startDate: String,
    var endDate: String,
    var status: String,
    var type: String
)