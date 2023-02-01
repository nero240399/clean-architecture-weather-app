package com.example.weatherjourney.weather.domain.repository

import com.example.weatherjourney.util.Result
import com.example.weatherjourney.weather.data.source.local.entity.LocationEntity
import com.example.weatherjourney.weather.domain.model.Coordinate
import com.example.weatherjourney.weather.domain.model.SuggestionCity

interface LocationRepository {

    suspend fun getCurrentCoordinate(): Result<Coordinate>

    suspend fun fetchSuggestionLocations(city: String): Result<List<SuggestionCity>>

    suspend fun checkIsLocationSaved(coordinate: Coordinate): Boolean

    suspend fun getCityByCoordinate(
        coordinate: Coordinate,
        forceCacheCurrentLocation: Boolean = false
    ): Result<String>

    suspend fun saveLocation(city: String, coordinate: Coordinate)

    suspend fun getLocations(): List<LocationEntity>

    suspend fun deleteLocation(location: LocationEntity)
}
