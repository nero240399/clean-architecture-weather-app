package com.example.weather.model.geocoding

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)
