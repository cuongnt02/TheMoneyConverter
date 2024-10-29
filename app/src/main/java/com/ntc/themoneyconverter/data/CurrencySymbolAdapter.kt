package com.ntc.themoneyconverter.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson


class CurrencySymbolAdapter {
    @FromJson
    fun fromJson(json: Map<String, String>) : CurrencyResponse {
        val currencies = ArrayList<Currency>()
        json.forEach { symbol ->
                currencies.add(Currency(symbol.key, symbol.value))
        }
        return CurrencyResponse(currencies = currencies)
    }

    @ToJson
    fun toJson(writer: JsonWriter, res: CurrencyResponse) {
        return
    }
}