package com.ntc.themoneyconverter.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    val currencies: List<Currency>
)