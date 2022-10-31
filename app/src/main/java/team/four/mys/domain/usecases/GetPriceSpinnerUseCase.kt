package team.four.mys.domain.usecases

class GetPriceSpinnerUseCase {

    fun getPriceSpinner(item: String): String {
        var price = ""
        when (item) {
            "USD" -> price = "$"
            "EUR" -> price = "€"
            "BYN" -> price = "Br"
        }
        return price
    }
}