package team.four.mys.domain.models

data class Subscriptions(
    var image: String? = null,
    var name: String? = null,
    var price: String? = null,
    var description: String? = null,
    var writeOffDate: String? = null,
    var priceSpinner: String? = null,
    var date: String? = null,
    var dateType: String? = null,
    val category: String? = null,
    val priceName: String? = null
)