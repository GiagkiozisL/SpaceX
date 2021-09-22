package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Fairings(val reused: Boolean,
                    @JsonProperty("recovery_attempt")
                    val recoveryAttempt: Boolean,
                    val recovered: Boolean,
                    val ship: Any? = null) {
}