package team.four.mys.data.repository

import team.four.mys.R
import team.four.mys.domain.models.Country

object CountryData {
    val country = arrayListOf(
        Country(R.drawable.language_russia, "Russian Federation", "+7"),
        Country(R.drawable.language_usa, "USA", "+1"),
        Country(R.drawable.language_russia, "Belarus", "+375")
    )
}