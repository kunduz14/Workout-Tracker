package com.kunduzamatbekova.android.workouttracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kunduzamatbekova.android.workouttracker.data.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAllWorkout(): Flow<List<Workout>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertWorkout(name: Workout)

    @Delete
    suspend fun deleteWorkout(name: Workout)
}