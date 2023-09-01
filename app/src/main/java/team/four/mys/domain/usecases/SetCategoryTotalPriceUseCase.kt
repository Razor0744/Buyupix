package team.four.mys.domain.usecases

import team.four.mys.domain.models.Valute

class SetCategoryTotalPriceUseCase {

    fun execute(price: Double, priceSpinner: String, valute: Valute?): Double {
        val totalPrice: Double = when (priceSpinner) {
            "BYN" -> price * valute?.BYN?.Value!! / valute.USD?.Value!!
            "EUR" -> price * valute?.EUR?.Value!! / valute.USD?.Value!!
            else -> price
        }
        return totalPrice
    }
}