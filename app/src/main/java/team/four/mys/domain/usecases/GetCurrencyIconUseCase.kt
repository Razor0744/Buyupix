package team.four.mys.domain.usecases

class GetCurrencyIconUseCase {

    fun execute(currency: String): String {
        return when (currency) {
            "USD" -> "$"
            "EUR" -> "€"
            "BYN" -> "Br"
            else -> "$"
        }
    }
}