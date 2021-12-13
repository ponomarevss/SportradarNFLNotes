package ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Type(
    @Expose val code: String
): Parcelable
