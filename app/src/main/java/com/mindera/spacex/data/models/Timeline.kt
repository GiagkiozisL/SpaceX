package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Timeline(@JsonProperty("webcast_liftoff")
                    val webcastLiftoff: Long) {
}
