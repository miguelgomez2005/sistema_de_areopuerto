package com.example.sistemadeareopuerto.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sistemadeareopuerto.model.Reservation
import com.example.sistemadeareopuerto.ui.theme.CardBlue
import com.example.sistemadeareopuerto.ui.theme.DeepBlue
import com.example.sistemadeareopuerto.ui.theme.GoldAccent
import com.example.sistemadeareopuerto.viewmodel.FlightViewModel

@Composable
fun ReservationsScreen(viewModel: FlightViewModel) {
    var editingReservation by remember { mutableStateOf<Reservation?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { Text("Mis Reservas Activas", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = DeepBlue) }
            if (viewModel.reservations.isEmpty()) {
                item { Text("No tienes reservas activas por el momento.", color = Color.Gray) }
            } else {
                items(viewModel.reservations) { res ->
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = CardBlue)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Destino: ${res.flight.destination} (${res.flight.country})", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = GoldAccent)
                            Text("ID Reserva: ${res.reservationId}", color = Color.White)
                            Text("Ida: ${res.departureDate} | Regreso: ${res.returnDate}", color = Color.White)
                            Text("Pasajeros: ${res.passengers}", color = Color.White)
                            Text("Total: $${String.format("%,.0f", res.totalPrice)}", fontWeight = FontWeight.Bold, color = GoldAccent)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = { editingReservation = res },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = GoldAccent)
                                ) {
                                    Text("Editar", color = DeepBlue, fontWeight = FontWeight.Bold)
                                }
                                Button(
                                    onClick = { viewModel.cancelReservation(res) },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text("Eliminar", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }

        editingReservation?.let { res ->
            var newDep by remember { mutableStateOf(res.departureDate) }
            var newRet by remember { mutableStateOf(res.returnDate) }
            var newPassStr by remember { mutableStateOf(res.passengers.toString()) }

            AlertDialog(
                onDismissRequest = { editingReservation = null },
                title = { Text("Editar Reserva ${res.reservationId}", color = DeepBlue) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(value = newDep, onValueChange = { newDep = it }, label = { Text("Nueva Fecha Ida") })
                        OutlinedTextField(value = newRet, onValueChange = { newRet = it }, label = { Text("Nueva Fecha Regreso") })
                        OutlinedTextField(value = newPassStr, onValueChange = { newPassStr = it }, label = { Text("Nuevos Pasajeros") })
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val pass = newPassStr.toIntOrNull() ?: res.passengers
                        viewModel.updateReservation(res.reservationId, newDep, newRet, pass)
                        editingReservation = null
                    }) {
                        Text("Guardar Cambios")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { editingReservation = null }) { Text("Cancelar") }
                }
            )
        }
    }
}