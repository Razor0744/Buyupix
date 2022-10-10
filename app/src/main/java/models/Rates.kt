package models

data class Rates(
    var rates: Currencies? = null
)

data class Currencies(
    var BYN: String? = null,
    var RUB: String? = null,
    var EUR: String? = null
)
