package com.mindera.spacex.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LaunchSite(@JsonProperty("site_id")
                      val siteID: String,
                      @JsonProperty("site_name")
                      val siteName: String,
                      @JsonProperty("site_name_long")
                      val siteNameLong: String) {
}