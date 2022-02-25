package jem.temidayo.myrestuarant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import jem.temidayo.myrestuarant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var phone: String = ""

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user === null){
//            redirect to login page if user
//            is not already logged in.
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        } else{
            phone = user.phoneNumber.toString()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        sendUserLoginData(phone)
    }

    private fun sendUserLoginData(phone: String?) {
        binding.welcomeText.setText("Hello $phone")
    }
}