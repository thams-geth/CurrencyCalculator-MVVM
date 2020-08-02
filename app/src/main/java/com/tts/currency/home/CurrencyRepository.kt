package com.tts.currency.home

import com.tts.currency.api.ApiInterface
import com.tts.currency.model.Currency
import retrofit2.Response

class CurrencyRepository(private val api: ApiInterface) {

    suspend fun getCurrencyRates(base: String): Currency {
        return api.getCurrencyRates(base)
    }


}