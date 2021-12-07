package jem.temidayo.myrestuarant.appIntro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jem.temidayo.myrestuarant.R
import jem.temidayo.myrestuarant.databinding.ActivityFeaturePurchaseBinding
import jem.temidayo.myrestuarant.databinding.ActivitySplashScreenBinding

class FeaturePurchase : AppCompatActivity() {

    private lateinit var binding: ActivityFeaturePurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturePurchaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}