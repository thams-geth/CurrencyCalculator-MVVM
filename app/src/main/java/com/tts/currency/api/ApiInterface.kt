package com.tts.currency.api

import com.tts.currency.model.Currency
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

//    https://api.exchangeratesapi.io/latest?base=USD


    @GET("latest")
    suspend fun getCurrencyRates(@Query("base") base: String): Currency

}