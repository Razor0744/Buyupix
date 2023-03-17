package team.four.mys.domain.usecases

import team.four.mys.domain.models.Valute

class SetCategoryTotalPriceUseCase {

    fun execute(price: Double, priceSpinner: String, valute: Valute?): Double {
        var totalPrice = 0.0
        when (priceSpinner) {
            "BYN" -> totalPrice = price * valute?.BYN?.Value!! / valute.USD?.Value!!
            "EUR" -> totalPrice = price * valute?.EUR?.Value!! / valute.USD?.Value!!
        }
        return totalPrice
    }
}