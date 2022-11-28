package team.four.mys.domain.usecases

class GetPriceSpinnerUseCase {

    fun execute(item: String): String {
        var price = ""
        when (item) {
            "USD" -> price = "$"
            "EUR" -> price = "€"
            "BYN" -> price = "Br"
        }
        return price
    }
}