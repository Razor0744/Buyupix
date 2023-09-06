package team.four.mys.domain.usecases

class GetCurrencyIconUseCase {

    fun execute(item: String): String {
        return when (item) {
            "USD" -> "$"
            "EUR" -> "€"
            "BYN" -> "Br"
            else -> "$"
        }
    }
}