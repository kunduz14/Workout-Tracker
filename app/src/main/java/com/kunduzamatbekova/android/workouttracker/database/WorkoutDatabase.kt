package com.kunduzamatbekova.android.workouttracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kunduzamatbekova.android.workouttracker.data.Workout

private const val DATABASE_NAME = "workout-database"

@Database(entities = [Workout::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract val workoutDao: WorkoutDao

    companion object {
        fun createWorkoutDatabase(context: Context): WorkoutDatabase {
            return Room.databaseBuilder(
                context,
                WorkoutDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}