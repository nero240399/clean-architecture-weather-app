package com.example.weatherjourney.domain

import com.example.weatherejourney.LocationPreferences
import com.example.weatherjourney.weather.domain.model.Coordinate
import com.example.weatherjourney.weather.domain.model.unit.TemperatureUnit
import com.example.weatherjourney.weather.domain.model.unit.WindSpeedUnit
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    val locationPreferencesFlow: Flow<LocationPreferences>

    val temperatureUnitFlow: Flow<TemperatureUnit>

    val windSpeedUnitFlow: Flow<WindSpeedUnit>

    suspend fun updateCityAddress(cityAddress: String)

    suspend fun updateLocation(cityAddress: String, coordinate: Coordinate, timeZone: String)

    suspend fun updateCoordinate(coordinate: Coordinate)

    suspend fun updateTimeZone(timeZone: String)

    suspend fun saveTemperatureUnit(unit: TemperatureUnit)

    suspend fun saveWindSpeedUnit(unit: WindSpeedUnit)
}
