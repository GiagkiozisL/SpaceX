package com.mindera.spacex.data.models

data class LaunchFailureDetails(val time: Long,
                                val altitude: Any? = null,
                                val reason: String) {
}