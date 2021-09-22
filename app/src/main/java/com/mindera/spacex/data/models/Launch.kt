package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Launch(@JsonProperty("flight_number")
                  val flightNumber: Long,
                  @JsonProperty("mission_name")
                  val missionName: String,
                  @JsonProperty("mission_id")
                  val missionID: List<Any?>,
                  val upcoming: Boolean,
                  @JsonProperty("launch_year")
                  val launchYear: String,
                  @JsonProperty("launch_date_unix")
                  val launchDateUnix: Long,
                  @JsonProperty("launch_date_utc")
                  val launchDateUTC: String,
                  @JsonProperty("launch_date_local")
                  val launchDateLocal: String,
                  @JsonProperty("is_tentative")
                  val isTentative: Boolean,
                  @JsonProperty("tentative_max_precision")
                  val tentativeMaxPrecision: String,
                  val tbd: Boolean,
                  @JsonProperty("launch_window")
                  val launchWindow: Long? = null,
                  val rocket: Rocket,
                  val ships: List<Any?>,
                  val telemetry: Telemetry,
                  @JsonProperty("launch_site")
                  val launchSite: LaunchSite,
                  @JsonProperty("launch_success")
                  val launchSuccess: Boolean,
                  @JsonProperty("launch_failure_details")
                  val launchFailureDetails: LaunchFailureDetails? = null,
                  val links: Links,
                  val details: String? = null,
                  @JsonProperty("static_fire_date_utc")
                  val staticFireDateUTC: String? = null,
                  @JsonProperty("static_fire_date_unix")
                  val staticFireDateUnix: Long,
                  val timeline: Timeline? = null,
                  val crew: Any? = null) {
}