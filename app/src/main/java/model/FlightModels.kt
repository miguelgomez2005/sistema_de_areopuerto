package com.example.sistemadeareopuerto.model

data class Flight(
    val id: String,
    val origin: String,
    val destination: String,
    val country: String,
    val time: String,
    val basePrice: Double,
    val imageRes: Int
)

data class Reservation(
    val reservationId: String,
    val flight: Flight,
    val departureDate: String,
    val returnDate: String,
    val passengers: Int,
    val totalPrice: Double
)
