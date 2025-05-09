package com.kunduzamatbekova.android.workouttracker.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kunduzamatbekova.android.workouttracker.WorkoutViewModel
import com.kunduzamatbekova.android.workouttracker.WorkoutViewModel.Companion.factory
import com.kunduzamatbekova.android.workouttracker.data.Workout

@Composable
fun WorkoutScreen(
    workoutViewModel: WorkoutViewModel = viewModel(factory = factory)
) {
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val workouts = workoutViewModel.workoutList
        .collectAsState(initial = emptyList()).value.sortedBy { it.name }

    val filteredWorkouts by remember(workouts, query) {
        derivedStateOf {
            if (query.isBlank()) {
                workouts
            } else {
                workouts.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChange = {
                query = it
                expanded = true
            },
            onClearClick = {
                query = ""
                expanded = false
            },
            onFocusChanged = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        )

        if (expanded) {
            if (filteredWorkouts.isEmpty()) {
                Button(
                    onClick = {
                        workoutViewModel.newWorkout.value = query
                        workoutViewModel.insertWorkout()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Добавить тренировку",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                FilteredList(
                    filteredWorkoutData = filteredWorkouts,
                    onItemClick = { workout ->
                        query = workout.name
                        expanded = false
                    },
                    onDeleteClick = {
                        workoutViewModel.workout = it
                        workoutViewModel.deleteWorkout()
                    }
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .padding(top = 8.dp)
            .onFocusChanged { onFocusChanged(it.isFocused) },
        placeholder = {
            Text(
                "Введите тренировку",
                color = Color.Gray
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        prefix = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.padding(end = 8.dp)
            )
        },
        shape = RoundedCornerShape(60.dp),
    )
}

@Composable
fun FilteredList(
    filteredWorkoutData: List<Workout>,
    onItemClick: (Workout) -> Unit,
    onDeleteClick: (Workout) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(filteredWorkoutData) { workout ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = workout.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp)
                        .clickable { onItemClick(workout) }
                )
                IconButton(
                    onClick = { onDeleteClick(workout) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
@Preview
fun Preview() {
    WorkoutScreen()
}