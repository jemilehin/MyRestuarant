package jem.temidayo.myrestuarant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import jem.temidayo.myrestuarant.RestuarantDataObjects.User
import jem.temidayo.myrestuarant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: User

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user === null){
//            redirect to login page if user
//            is not already logged in.
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        } else{
//            phone = user.phoneNumber.toString()
            Log.d("userInfo","${user.displayName}")
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        user = User()

        getUserProfile()
    }

   private fun getUserProfile() {
       val cUser = auth.currentUser
       if (cUser != null) {
           val uname = cUser.displayName
           val email = cUser.email
           val phone = cUser.phoneNumber

           user.email = email.toString()
           user.name = uname.toString()
           user.phone_number = phone.toString()

           Log.d("currentUser", uname.toString())
       }
   }
}