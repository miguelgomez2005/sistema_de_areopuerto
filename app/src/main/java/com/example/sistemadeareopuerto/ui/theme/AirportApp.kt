package com.example.sistemadeareopuerto.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.sistemadeareopuerto.ui.screens.FlightStatsScreen
import com.example.sistemadeareopuerto.ui.screens.HomeScreen
import com.example.sistemadeareopuerto.ui.screens.ReservationsScreen
import com.example.sistemadeareopuerto.ui.theme.DeepBlue
import com.example.sistemadeareopuerto.ui.theme.GoldAccent
import com.example.sistemadeareopuerto.viewmodel.FlightViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirportApp(viewModel: FlightViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Text(text = "VILLARREAL", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = GoldAccent, letterSpacing = 2.sp)
                        Text(text = "FLIGHT AGENCY", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.White, letterSpacing = 3.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DeepBlue)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = DeepBlue) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio", tint = if (currentRoute == "home") GoldAccent else Color.LightGray) },
                    label = { Text("Inicio", color = if (currentRoute == "home") GoldAccent else Color.LightGray) },
                    selected = currentRoute == "home",
                    onClick = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Estadísticas", tint = if (currentRoute == "stats") GoldAccent else Color.LightGray) },
                    label = { Text("Estadísticas", color = if (currentRoute == "stats") GoldAccent else Color.LightGray) },
                    selected = currentRoute == "stats",
                    onClick = {
                        navController.navigate("stats") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Reservas", tint = if (currentRoute == "reservations") GoldAccent else Color.LightGray) },
                    label = { Text("Reservas", color = if (currentRoute == "reservations") GoldAccent else Color.LightGray) },
                    selected = currentRoute == "reservations",
                    onClick = {
                        navController.navigate("reservations") {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel = viewModel) }
            composable("stats") { FlightStatsScreen(viewModel = viewModel) }
            composable("reservations") { ReservationsScreen(viewModel = viewModel) }
        }
    }
}
