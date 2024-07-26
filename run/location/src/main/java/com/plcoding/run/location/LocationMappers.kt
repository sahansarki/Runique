package com.plcoding.run.location

import android.location.Location
import com.plcoding.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.plcoding.core.domain.location.Location(latitude, longitude),
        altitude = altitude
    )
}