package jem.temidayo.myrestuarant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import jem.temidayo.myrestuarant.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var  refUsers: DatabaseReference
    private  var firebaseUserID : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener({
            registerUser()
        })
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
            auth.createUserWithEmailAndPassword(phone, password).addOnCompleteListener(this) { task ->  // <<< CHANGE WAS MADE HERE !
                if (task.isSuccessful) {
                    firebaseUserID = auth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID
                    )
                } else {
                    Toast.makeText(this, "Error Message"+ task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}