package com.kunduzamatbekova.android.workouttracker

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kunduzamatbekova.android.workouttracker.data.Workout
import com.kunduzamatbekova.android.workouttracker.database.WorkoutApplication
import com.kunduzamatbekova.android.workouttracker.database.WorkoutDatabase
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class WorkoutViewModel(val database: WorkoutDatabase) : ViewModel() {

    val workoutList = database.workoutDao.getAllWorkout()
    var workout by mutableStateOf<Workout?>(null)
    var newWorkout = mutableStateOf("")

    fun insertWorkout() = viewModelScope.launch {
        val workout = workout?.copy(name = newWorkout.value)
            ?: Workout(name = newWorkout.value)
        database.workoutDao.insertWorkout(workout)
    }

    fun deleteWorkout() = viewModelScope.launch {
        workout?.let { workout ->
            database.workoutDao.deleteWorkout(workout)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    val database =
                        (checkNotNull(extras[APPLICATION_KEY]) as WorkoutApplication).database
                    return WorkoutViewModel(database) as T
                }
            }
    }
}