package com.example.sistemadeareopuerto.data

import androidx.compose.runtime.mutableStateListOf
import com.example.sistemadeareopuerto.R
import com.example.sistemadeareopuerto.model.Flight
import com.example.sistemadeareopuerto.model.Reservation

class FlightRepository {

    val flights = mutableStateListOf(
        Flight("V-101", "Bogotá (BOG)", "París", "Francia", "08:00 AM", 2500000.0, R.drawable.paris),
        Flight("V-102", "Bogotá (BOG)", "Madrid", "España", "10:30 AM", 1800000.0, R.drawable.madrir),
        Flight("V-103", "Bogotá (BOG)", "Tokio", "Japón", "01:15 PM", 4200000.0, R.drawable.tokio),
        Flight("V-104", "Bogotá (BOG)", "Roma", "Italia", "03:00 PM", 2100000.0, R.drawable.roma),
        Flight("V-105", "Bogotá (BOG)", "Berlín", "Alemania", "06:00 PM", 2300000.0, R.drawable.berlin),
        Flight("V-106", "Bogotá (BOG)", "Estocolmo", "Suecia", "09:00 PM", 2800000.0, R.drawable.estocolmo)
    )

    val reservations = mutableStateListOf<Reservation>()

    fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
    }

    fun updateReservation(updated: Reservation) {
        val index = reservations.indexOfFirst { it.reservationId == updated.reservationId }
        if (index != -1) {
            reservations[index] = updated
        }
    }

    fun deleteReservation(reservation: Reservation) {
        reservations.remove(reservation)
    }
}