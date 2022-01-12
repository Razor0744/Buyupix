package Model

class Subscription(
    var name: String,
    var image: String,
    var cost: String,
    var category: String,
    var date: String,
    var costSpinner:String
) {
    override fun toString(): String {
        return name
    }
}