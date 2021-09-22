package com.mindera.spacex.data.models

data class SecondStage(val block: Long,
                       val payloads: List<Payload>) {
}