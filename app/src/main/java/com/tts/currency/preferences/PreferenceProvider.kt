package com.tts.currency.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


private const val AUTH_TOKEN = "authToken"
private const val BASE_CURRENCY = "base"
private const val CURRENCY_CODE_LIST = "currencycodelist"
private const val LAST_LOGIN_TIME = "lastLoginTime"


class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveBaseCurrency(currency: String) {
        preference.edit().putString(BASE_CURRENCY, currency).apply()
    }

    fun getBaseCurrency(): String? {
        return preference.getString(BASE_CURRENCY, "INR")
    }

    fun saveCurrencyCode(code: Set<String>) {
        preference.edit().putStringSet(CURRENCY_CODE_LIST, code).apply()
    }

    fun getCurrencyCode(): Set<String>? {
        return preference.getStringSet(CURRENCY_CODE_LIST, null)
    }

}