package jem.temidayo.myrestuarant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import jem.temidayo.myrestuarant.databinding.ActivityMainBinding
import jem.temidayo.myrestuarant.databinding.ActivityPhoneVerificationBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val textView = binding.welcomeText

        auth = FirebaseAuth.getInstance()
        val currentUser = intent.getStringExtra("currentUser").toString()

        binding.welcomeText.setText("Hello $currentUser")
    }
}