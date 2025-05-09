package com.kunduzamatbekova.android.workouttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
)