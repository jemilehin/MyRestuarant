package jem.temidayo.myrestuarant.RestuarantDataObjects

data class UserProfile(val firstname: String, val lastname: String, var phone_number: Int,
                        var password: String, var email: String, val gender: String)
