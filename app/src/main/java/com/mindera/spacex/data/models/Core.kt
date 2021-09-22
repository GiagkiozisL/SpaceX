package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Core( @JsonProperty("core_serial")
                 val coreSerial: String? = null,
                 val flight: Long? = null,
                 val block: Any? = null,
                 val gridfins: Boolean? = null,
                 val legs: Boolean? = null,
                 val reused: Boolean? = null,
                 @JsonProperty("land_success")
                 val landSuccess: Any? = null,
                 @JsonProperty("landing_intent")
                 val landingIntent: Boolean? = null,
                 @JsonProperty("landing_type")
                 val landingType: Any? = null,
                 @JsonProperty("landing_vehicle")
                 val landingVehicle: Any? = null) {
}