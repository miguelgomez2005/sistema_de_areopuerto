package com.example.sistemadeareopuerto.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sistemadeareopuerto.data.FlightRepository
import com.example.sistemadeareopuerto.model.Flight
import com.example.sistemadeareopuerto.model.Reservation

class FlightViewModel : ViewModel() {
    private val repository = FlightRepository()

    var searchQuery by mutableStateOf("")

    val flights: List<Flight> get() = repository.flights
    val reservations: List<Reservation> get() = repository.reservations

    fun addReservation(flight: Flight, departureDate: String, returnDate: String, passengers: Int) {
        val total = flight.basePrice * passengers
        val newRes = Reservation(
            reservationId = "RES-${System.currentTimeMillis().toString().takeLast(4)}",
            flight = flight,
            departureDate = departureDate,
            returnDate = returnDate,
            passengers = passengers,
            totalPrice = total
        )
        repository.addReservation(newRes)
    }

    fun updateReservation(reservationId: String, newDeparture: String, newReturn: String, newPassengers: Int) {
        val current = reservations.find { it.reservationId == reservationId } ?: return
        val updated = current.copy(
            departureDate = newDeparture,
            returnDate = newReturn,
            passengers = newPassengers,
            totalPrice = current.flight.basePrice * newPassengers
        )
        repository.updateReservation(updated)
    }

    fun cancelReservation(reservation: Reservation) {
        repository.deleteReservation(reservation)
    }
}