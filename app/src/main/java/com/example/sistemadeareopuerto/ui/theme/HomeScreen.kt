package com.example.sistemadeareopuerto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadeareopuerto.model.Flight
import com.example.sistemadeareopuerto.ui.theme.DeepBlue
import com.example.sistemadeareopuerto.ui.theme.GoldAccent
import com.example.sistemadeareopuerto.viewmodel.FlightViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: FlightViewModel) {
    var selectedFlightForBooking by remember { mutableStateOf<Flight?>(null) }

    val filteredFlights = viewModel.flights.filter {
        it.destination.contains(viewModel.searchQuery, ignoreCase = true) ||
                it.country.contains(viewModel.searchQuery, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text("Destinos Internacionales", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = DeepBlue)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.searchQuery = it },
                    label = { Text("Buscar destino...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            items(filteredFlights) { flight ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = flight.imageRes),
                            contentDescription = flight.destination,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${flight.destination} (${flight.country})", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = DeepBlue)
                            Text("Salida: ${flight.origin} | Hora: ${flight.time}", color = Color.DarkGray)
                            Text("Precio base: $${String.format("%,.0f", flight.basePrice)}", fontWeight = FontWeight.Bold, color = GoldAccent)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { selectedFlightForBooking = flight },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = DeepBlue)
                            ) {
                                Text("Reservar Destino", color = GoldAccent, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        selectedFlightForBooking?.let { flight ->
            var departureDate by remember { mutableStateOf("2026-12-01") }
            var returnDate by remember { mutableStateOf("2026-12-15") }
            var passengersStr by remember { mutableStateOf("1") }
            val passengers = passengersStr.toIntOrNull() ?: 1
            val estimatedTotal = flight.basePrice * passengers

            AlertDialog(
                onDismissRequest = { selectedFlightForBooking = null },
                title = { Text("Reservar hacia ${flight.destination}", color = DeepBlue) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(value = departureDate, onValueChange = { departureDate = it }, label = { Text("Fecha de Ida (AAAA-MM-DD)") })
                        OutlinedTextField(value = returnDate, onValueChange = { returnDate = it }, label = { Text("Fecha de Regreso (AAAA-MM-DD)") })
                        OutlinedTextField(value = passengersStr, onValueChange = { passengersStr = it }, label = { Text("Número de Pasajeros") }, singleLine = true)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Total estimado: $${String.format("%,.0f", estimatedTotal)}", fontWeight = FontWeight.Bold, color = GoldAccent, fontSize = 16.sp)
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.addReservation(flight, departureDate, returnDate, passengers)
                        selectedFlightForBooking = null
                    }) {
                        Text("Confirmar Reserva")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { selectedFlightForBooking = null }) { Text("Cancelar") }
                }
            )
        }
    }
}