package com.tts.currency.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tts.currency.home.CurrencyRepository
import com.tts.currency.model.Currency
import com.tts.currency.preferences.PreferenceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CurrencyViewModel(
    private val currencyRepository: CurrencyRepository,
    private val preferenceProvider: PreferenceProvider
) : ViewModel() {
    val mutableLiveData: MutableLiveData<Currency> = MutableLiveData<Currency>()
    var stateLiveData: LiveData<Currency> = mutableLiveData


    fun onRefresh(view: View) {

        CoroutineScope(Dispatchers.Main).launch {
            val response =
                currencyRepository.getCurrencyRates(base = preferenceProvider.getBaseCurrency()!!)
            mutableLiveData.value = response
        }
    }


}