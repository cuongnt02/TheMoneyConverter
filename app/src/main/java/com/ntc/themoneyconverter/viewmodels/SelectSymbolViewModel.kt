package com.ntc.themoneyconverter.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntc.themoneyconverter.data.Currency
import com.ntc.themoneyconverter.data.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "SelectSymbolViewModel"
class SelectSymbolViewModel : ViewModel() {
    private val currencyRepository = CurrencyRepository()

    private val _currencies: MutableStateFlow<List<Currency>> = MutableStateFlow(emptyList())

    val currencies: StateFlow<List<Currency>>
        get() = _currencies.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val response = currencyRepository.fetchCurrencies()
                _currencies.value = response
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch currency symbols", ex)
            }
        }
    }
}