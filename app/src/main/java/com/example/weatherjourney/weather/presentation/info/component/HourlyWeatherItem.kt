package com.example.weatherjourney.weather.presentation.info.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherjourney.R
import com.example.weatherjourney.util.roundTo
import com.example.weatherjourney.weather.domain.model.unit.WindSpeedUnit
import com.example.weatherjourney.weather.domain.model.weather.HourlyWeather
import kotlin.math.roundToInt

@Composable
fun HourlyWeatherItem(
    hourly: HourlyWeather,
    windSpeedUnit: WindSpeedUnit?,
    modifier: Modifier = Modifier
) {
    windSpeedUnit?.let {
        Row(
            modifier
                .padding(start = 16.dp, end = 32.dp)
                .height(50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                hourly.date.asString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.width(80.dp)
            )
            Image(
                painter = painterResource(hourly.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
            Text(
                stringResource(R.string.temperature, hourly.temp.roundToInt()),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.width(40.dp)
            )
            Text(
                "${hourly.windSpeed.roundTo(1)}${windSpeedUnit.label}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
