package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeeklySchedule(
    @Expose val id: String,
    @Expose val year: Int,
    @Expose val type: String,
    @Expose val name: String,
    @Expose
    @SerializedName("week")val scheduleWeek: ScheduleWeek
): Parcelable
