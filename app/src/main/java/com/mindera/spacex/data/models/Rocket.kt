package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Rocket( @JsonProperty("rocket_id")
                   val rocketID: String,
                   @JsonProperty("rocket_name")
                   val rocketName: String,
                   @JsonProperty("rocket_type")
                   val rocketType: String,
                   @JsonProperty("first_stage")
                   val firstStage: FirstStage,
                   @JsonProperty("second_stage")
                   val secondStage: SecondStage,
                   val fairings: Fairings? = null) {
}