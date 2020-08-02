package com.tts.currency.model

data class Currency(
    val base: String,
    val date: String,
    val rates: Rates
)