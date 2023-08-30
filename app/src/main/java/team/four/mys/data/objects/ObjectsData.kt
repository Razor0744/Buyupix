package team.four.mys.data.objects

import team.four.mys.domain.models.Currencies
import team.four.mys.domain.models.DarkMode
import team.four.mys.domain.models.Language

object ObjectsData {

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

    val darkMode = arrayListOf(
        DarkMode("Dark Theme"),
        DarkMode("Light Theme"),
        DarkMode("System Theme")
    )

    val language = arrayListOf(
        Language("language_usa", "USA"),
        Language("language_russia", "Russia")
    )
}