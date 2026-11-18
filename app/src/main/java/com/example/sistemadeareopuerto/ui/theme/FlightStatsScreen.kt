package com.example.sistemadeareopuerto.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadeareopuerto.ui.theme.DeepBlue
import com.example.sistemadeareopuerto.ui.theme.GoldAccent
import com.example.sistemadeareopuerto.viewmodel.FlightViewModel

@Composable
fun FlightStatsScreen(viewModel: FlightViewModel) {
    val totalDestinations = viewModel.flights.size
    val averagePrice = if (totalDestinations > 0) viewModel.flights.map { it.basePrice }.average() else 0.0
    val mostExpensive = viewModel.flights.maxByOrNull { it.basePrice }
    val activeReservationsCount = viewModel.reservations.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Estadísticas del Negocio", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = DeepBlue)
            Text("Indicadores clave de Villarreal Flight Agency", fontSize = 14.sp, color = Color.Gray)
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Destinos Internacionales Activos", fontWeight = FontWeight.Bold, color = DeepBlue)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("$totalDestinations ciudades disponibles", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = GoldAccent)
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Precio Promedio Base por Vuelo", fontWeight = FontWeight.Bold, color = DeepBlue)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("$${String.format("%,.0f", averagePrice)}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = GoldAccent)
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Destino Exclusivo (Mayor Costo)", fontWeight = FontWeight.Bold, color = DeepBlue)
                    Spacer(modifier = Modifier.height(4.dp))
                    if (mostExpensive != null) {
                        Text("${mostExpensive.destination} (${mostExpensive.country})", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DeepBlue)
                        Text("Precio: $${String.format("%,.0f", mostExpensive.basePrice)}", color = GoldAccent, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total de Reservas Registradas", fontWeight = FontWeight.Bold, color = DeepBlue)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("$activeReservationsCount reservas en curso", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = GoldAccent)
                }
            }
        }
    }
}