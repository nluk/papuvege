package pl.nluk.papuvege.models

data class Order(
    val items : List<OrderItem>
){
    var total = 0.0
}
