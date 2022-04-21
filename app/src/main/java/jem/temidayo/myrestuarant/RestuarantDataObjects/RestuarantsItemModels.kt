package jem.temidayo.myrestuarant.RestuarantDataObjects

import java.util.Collections.emptyList


data class Food(val id: Int, val name: String, var description: String,
                var price: Int )

data class  Drink(val id: Int, val name: String,var price: Int)

data class Package(var id: Int, var name: String, var price: Int,
                   var description: String, var content: MutableList<String>)

data class Invoice(val id: Int, val order_number: String, val reference_number: Int,
                val description: String, val quantity: Int, val price: String,
                val total_cost: String)

data class RestuarantAdrress(var restuarantName: String, var email: String, var contact: String, var address: List<String>){
    constructor(): this("","","", emptyList())
}