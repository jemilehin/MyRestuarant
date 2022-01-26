package jem.temidayo.myrestuarant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import jem.temidayo.myrestuarant.RestuarantDataObjects.UserProfile
import jem.temidayo.myrestuarant.appIntro.AppfeaturesPager
import jem.temidayo.myrestuarant.databinding.ActivityRegisterBinding
import java.util.concurrent.TimeUnit


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

        binding.backButton.setOnClickListener {

        }
    }

    private fun registerUser() {
        val username: String = binding.editTextName.text.toString()
        val phone: String = binding.editTextPhone.text.toString()
        val password: String = binding.editTextPassword.text.toString()

        if(username == ""){
            Toast.makeText(this, "Please Provide your name", Toast.LENGTH_LONG).show()
        }else if(phone == ""){
            Toast.makeText(this, "Please Provide an active mobile number", Toast.LENGTH_LONG).show()
        }else if(password == ""){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
        }else{
            val intent = Intent(this, PhoneVerification::class.java)
            intent.putExtra("Verify", phone)
            startActivity(intent)
        }
    }

}