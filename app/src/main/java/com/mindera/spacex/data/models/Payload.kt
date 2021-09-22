package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Payload(@JsonProperty("payload_id")
                   val payloadID: String,
                   @JsonProperty("norad_id")
                   val noradID: List<Any?>,
                   val reused: Boolean,
                   val customers: List<String>,
                   val nationality: String? = null,
                   val manufacturer: String? = null,
                   @JsonProperty("payload_type")
                   val payloadType: String,
                   @JsonProperty("payload_mass_kg")
                   val payloadMassKg: Long,
                   @JsonProperty("payload_mass_lbs")
                   val payloadMassLbs: Long,
                   val orbit: String,
                   @JsonProperty("orbit_params")
                   val orbitParams: OrbitParams) {
}