package pl.nluk.papuvege.models

open class MenuItem : MenuDisplay {
    override val type: Int = MenuDisplay.ITEM

    var id: Long = 0L
    var name: String = ""
    var description = ""
    var price: Double = 0.0
    var imageURL: String = ""

    fun priceString() = "%.2f".format(price)

    override fun toString(): String = "Item: $name $price"
}
