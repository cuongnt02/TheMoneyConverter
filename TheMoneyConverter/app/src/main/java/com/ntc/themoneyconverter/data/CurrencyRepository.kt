package com.ntc.themoneyconverter.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class CurrencyRepository {
    private val openExchangeRatesAPI : OpenExchangeRatesAPI

    init {
        val moshi = Moshi.Builder().add(CurrencySymbolAdapter()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        openExchangeRatesAPI = retrofit.create()
    }

    suspend fun fetchCurrencies(): List<Currency> = openExchangeRatesAPI.fetchCurrencies().currencies
}