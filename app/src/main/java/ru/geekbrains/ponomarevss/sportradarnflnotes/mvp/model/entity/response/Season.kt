package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    @Expose val id: String,
    @Expose val year: Int,
    @Expose val startDate: String,
    @Expose val endDate: String,
    @Expose val status: String,
    @Expose val type: Type
): Parcelable
