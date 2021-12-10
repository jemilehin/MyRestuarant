package jem.temidayo.myrestuarant.appIntro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import jem.temidayo.myrestuarant.R
import jem.temidayo.myrestuarant.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_screen)
        binding.logo.startAnimation(fadeIn)

        Handler().postDelayed({
            val intent = Intent(this, AppfeaturesPager::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}