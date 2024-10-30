package com.ntc.themoneyconverter.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RateRepository {
    private val openExchangeRatesAPI: OpenExchangeRatesAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        openExchangeRatesAPI = retrofit.create()
    }

    suspend fun fetchRate(symbol: String) = openExchangeRatesAPI.fetchRates(symbol)
}