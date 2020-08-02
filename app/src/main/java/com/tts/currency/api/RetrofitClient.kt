package com.tts.currency.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.exchangeratesapi.io/"


class RetrofitClient(private val context: Context) {

    fun getClient(): ApiInterface {


//        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//            this.level = HttpLoggingInterceptor.Level.HEADERS
//        }
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .build()

        return Retrofit.Builder()
//            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }
}