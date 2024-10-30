package com.ntc.themoneyconverter.data

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenExchangeRatesAPI {
    @GET(
        "api/currencies.json")
    suspend fun fetchCurrencies() : CurrencyResponse

    @GET(
        "api/latest.json?app_id="
        + "5f726947942c46f6b650d5eb8381d82e"
    )
    suspend fun fetchRates(@Query("symbols") symbol: String) : RateResponse
}