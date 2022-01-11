package Model

class Subscription(val name: String, val image: String, val cost: String) {
    override fun toString(): String {
        return name
    }
}