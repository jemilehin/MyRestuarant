package jem.temidayo.myrestuarant.RestuarantDataObjects

data class Food(val id: Int, val name: String, var description: String,
                var price: Int )

data class  Drink(val id: Int, val name: String,var price: Int)

data class Package(val id: Int, val name: String, var price: Int,
                   var content: String)

data class Invoice(val id: Int, val order_number: String, val reference_number: Int,
                val description: String, val quantity: Int, val price: String,
                val total_cost: String)