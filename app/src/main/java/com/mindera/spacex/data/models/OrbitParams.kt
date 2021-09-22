package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrbitParams(@JsonProperty("reference_system")
                       val referenceSystem: String? = null,
                       val regime: String? = null,
                       val longitude: Any? = null,
                       @JsonProperty("semi_major_axis_km")
                       val semiMajorAxisKM: Any? = null,
                       val eccentricity: Any? = null,
                       @JsonProperty("periapsis_km")
                       val periapsisKM: Long? = null,
                       @JsonProperty("apoapsis_km")
                       val apoapsisKM: Long? = null,
                       @JsonProperty("inclination_deg")
                       val inclinationDeg: Long? = null,
                       @JsonProperty("period_min")
                       val periodMin: Any? = null,
                       @JsonProperty("lifespan_years")
                       val lifespanYears: Any? = null,
                       val epoch: Any? = null,
                       @JsonProperty("mean_motion")
                       val meanMotion: Any? = null,
                       val raan: Any? = null,
                       @JsonProperty("arg_of_pericenter")
                       val argOfPericenter: Any? = null,
                       @JsonProperty("mean_anomaly")
                       val meanAnomaly: Any? = null) {
}