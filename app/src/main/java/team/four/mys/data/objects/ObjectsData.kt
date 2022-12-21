package team.four.mys.data.objects

import team.four.mys.R
import team.four.mys.domain.models.*

object ObjectsData {

    val alert = arrayListOf(
        Alert("The day before the write-off"),
        Alert("Two days before cancellation"),
        Alert("Three days before cancellation")
    )

    val country = arrayListOf(
        Country(R.drawable.language_russia, "Russian Federation", "+7"),
        Country(R.drawable.language_usa, "USA", "+1"),
        Country(R.drawable.language_russia, "Belarus", "+375")
    )

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