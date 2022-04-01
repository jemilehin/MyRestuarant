package jem.temidayo.myrestuarant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jem.temidayo.myrestuarant.RestuarantDataObjects.User
import jem.temidayo.myrestuarant.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerButton.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {
        val username: String = binding.editTextName.text.toString()
        val phone: String = binding.editTextPhone.text.toString()
        val password: String = binding.editTextPassword.text.toString()
        val email: String = binding.editTextEmailAddress.text.toString()

        val user = User(username,phone,password,email)

        if(username == ""){
            Toast.makeText(this, "Please Provide your name", Toast.LENGTH_LONG).show()
        }else if(phone == ""){
            Toast.makeText(this, "Please Provide an active mobile number", Toast.LENGTH_LONG).show()
        }else if(password == ""){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
        }else{
//            val intent = Intent(this, PhoneVerification::class.java)
    val intent = Intent(this, PhoneVerification::class.java)
            intent.putExtra("Phone", user.phone_number)
            intent.putExtra("Username", user.name)
            intent.putExtra("Password", user.password)
            intent.putExtra("Email", user.email)
            startActivity(intent)
        }
    }

}