package jem.temidayo.myrestuarant

import android.R.attr.phoneNumber
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import jem.temidayo.myrestuarant.RestuarantDataObjects.User
import jem.temidayo.myrestuarant.databinding.ActivityPhoneVerificationBinding
import java.util.concurrent.TimeUnit


class PhoneVerification : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneVerificationBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private  var storedVerificationId : String = ""
    private lateinit var user : User

    private lateinit var forceResendingToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid


        val phone : String = intent.getStringExtra("Phone").toString()
        val username : String = intent.getStringExtra("Username").toString()
        val password : String = intent.getStringExtra("Password").toString()
        val email: String = intent.getStringExtra("Email").toString()
        sendVerificationCode(phone)

        user = User(username, phone, password,email)

        databaseReference = Firebase.database.reference

        binding.textResendButton.setOnClickListener {
            resendVerificationCode(phone, forceResendingToken)
        }

        binding.buttonVerifyCode.setOnClickListener {
            val codeToString : String = binding.oTPcode.text.toString()
            if(codeToString == "" || codeToString.length < 6){
                Toast.makeText(this, "OTP CODE: $codeToString", Toast.LENGTH_LONG).show()
                binding.oTPcode.error = "Wrong OTP Code..."
                binding.oTPcode.requestFocus()
            }else{
                Toast.makeText(this, "NOT EMPTY: $codeToString", Toast.LENGTH_LONG).show()
                verifyPhoneNumberWithCode(codeToString)
            }
        }
    }

    private fun sendVerificationCode(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+234" + phone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)               // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resendVerificationCode(phone: String, token: PhoneAuthProvider.ForceResendingToken){
        val options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("234" + phone) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .setForceResendingToken(token) // ForceResendingToken from callbacks
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            val code : String = credential.smsCode.toString()


            if(code != null){
                Log.d(TAG, "CODE:$code")
                verifyPhoneNumberWithCode(code)
            }else{
                Toast.makeText(this@PhoneVerification, "Type code sent", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            Toast.makeText(this@PhoneVerification, e.message, Toast.LENGTH_SHORT).show()
            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(this@PhoneVerification, e.message, Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
//                binding.buttonVerifyCode.isEnabled = false
                Log.i(TAG, "onVerifacationFailed: ${e.message.toString()}")

            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")


            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            forceResendingToken = token
        }
    }

    private fun verifyPhoneNumberWithCode(code: String) {
        // [START verify_with_code]
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        Log.d(TAG, "CODE: $code")
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        var userId : String = ""
                        val phone = auth.currentUser?.phoneNumber
                        databaseReference.child("Users").child(userId).setValue(user)
                        val intent = Intent(this, MapRestuarants::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        createUserWithEmailAndPassword(user.email,user.password)
                        Log.i("user", user.toString())
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                            Log.i("Sign-In", task.exception.toString())
                        }
                        // Update UI
                    }
                }
    }

    private fun createUserWithEmailAndPassword(email: String,password:String){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

    }

}
