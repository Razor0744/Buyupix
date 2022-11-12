package team.four.buyupix.data.repository

import team.four.buyupix.domain.models.Currencies

object CurrenciesData {
    val currencies = arrayListOf(
        Currencies("USD"),
        Currencies("EUR"),
        Currencies("BYN"),
        Currencies("AUD"),
        Currencies("AZN"),
        Currencies("ALL"),
        Currencies("DZD"),
        Currencies("BGN"),
        Currencies("BRL"),
        Currencies("RUB")
    )
}