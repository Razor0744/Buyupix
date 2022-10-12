package models

data class Rates(
    var rates: Currencies? = null
)

data class Currencies(
    var BYN: Float? = null,
    var RUB: Float? = null,
    var EUR: Float? = null
)
