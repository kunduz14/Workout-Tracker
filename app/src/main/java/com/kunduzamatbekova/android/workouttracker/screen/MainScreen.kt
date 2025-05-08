package com.kunduzamatbekova.android.workouttracker.screen

import com.kunduzamatbekova.android.workouttracker.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.kunduzamatbekova.android.workouttracker.data.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val navigationItem = listOf(
        NavigationItem("Главная", ImageVector.vectorResource(R.drawable.ic_home)),
        NavigationItem("Тренировка", ImageVector.vectorResource(R.drawable.ic_workout)),
        NavigationItem("Питание", ImageVector.vectorResource(R.drawable.ic_food)),
        NavigationItem("Профиль", ImageVector.vectorResource(R.drawable.ic_person)),
    )

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Text(navigationItem[selectedIndex].label)
                }
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ) {
                    navigationItem.forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            icon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = "Icon",
                                    modifier = Modifier.size(32.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.inversePrimary
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> WorkoutScreen()
                2 -> FoodScreen()
                3 -> ProfileScreen()
            }
        }
    }
}