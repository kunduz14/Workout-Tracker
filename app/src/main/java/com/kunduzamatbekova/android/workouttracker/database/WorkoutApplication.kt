package com.kunduzamatbekova.android.workouttracker.database

import android.app.Application

class WorkoutApplication : Application() {
    val database by lazy {
        WorkoutDatabase.createWorkoutDatabase(this)
    }
}