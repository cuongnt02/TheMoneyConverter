package com.ntc.themoneyconverter.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntc.themoneyconverter.data.Currency
import com.ntc.themoneyconverter.data.CurrencyRepository
import com.ntc.themoneyconverter.data.RateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal


private const val TAG = "ConvertViewModel"
class ConvertViewModel : ViewModel() {
    private val rateRepository = RateRepository()

    val currentAmount: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val convertFromSymbol: MutableStateFlow<String> = MutableStateFlow("USD")
    val convertToSymbol: MutableStateFlow<String> = MutableStateFlow("USD")
    val logMessage: MutableStateFlow<String> = MutableStateFlow("")
    var firstSetFrom = true
    var firstSetTo = true


    suspend fun fetchAndConvert() {
        viewModelScope.run {
            try {
                val fromResponse = rateRepository.fetchRate(convertFromSymbol.value)
                val toResponse = rateRepository.fetchRate(convertToSymbol.value)

                val fromValue = fromResponse.rates.entries.firstOrNull()?.value
                val toValue = toResponse.rates.entries.firstOrNull()?.value

                val message = StringBuilder().append(currentAmount.value.toBigDecimal().toPlainString())
                    .append(" ")
                    .append(fromResponse.rates.entries.firstOrNull()?.key)
                    .append(" can be exchanged into ")
                    .append(convert(currentAmount.value, toValue!! / fromValue!!).toPlainString())
                    .append(toResponse.rates.entries.firstOrNull()?.key)

                logMessage.value = message.toString()
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch currency symbols", ex)
            }
        }
    }

    private fun convert(amount: Double, rate: Double): BigDecimal {
        return (amount * rate).toBigDecimal()
    }

}