package jem.temidayo.myrestuarant.RestuarantDataObjects

data class User(var name: String, var phone_number: String, var password: String, var email: String,){
    constructor() : this("", "","",""
    )
}
