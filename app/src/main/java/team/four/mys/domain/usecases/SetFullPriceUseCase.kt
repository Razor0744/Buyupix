package team.four.mys.domain.usecases

class SetFullPriceUseCase {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null
    private var fullPrice: Float? = null

    fun execute(): Float? {
        while (true) {
            if (EUR != null && BYN != null && priceBYN != null && priceUSD != null && priceEUR != null) {
                fullPrice = priceUSD!! + (priceBYN!! / BYN!!) + (priceEUR!! / EUR!!)
                break
            }
        }
        return fullPrice
    }
}