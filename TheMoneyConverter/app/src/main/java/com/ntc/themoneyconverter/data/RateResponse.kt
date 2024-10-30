package com.ntc.themoneyconverter.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RateResponse(
    val base: String,
    val rates: Map<String, Double>
)