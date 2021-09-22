package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Company (val name: String,
                   val founder: String,
                   val founded: Long,
                   val employees: Long,
                   val vehicles: Long,
                   @JsonProperty("launch_sites")
                   val launchSites: Long,
                   @JsonProperty("test_sizes")
                   val testSites: Long,
                   val ceo: String,
                   val cto: String,
                   val coo: String,
                   @JsonProperty("cto_propulsion")
                   val ctoPropulsion: String,
                   val valuation: Long,
                   val headquarters: Headquarters,
                   val links: Links,
                   val summary: String
) {
}