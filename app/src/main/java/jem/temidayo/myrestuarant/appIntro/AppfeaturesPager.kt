package jem.temidayo.myrestuarant.appIntro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import jem.temidayo.myrestuarant.appIntro.features.Features1
import jem.temidayo.myrestuarant.appIntro.features.Features2
import jem.temidayo.myrestuarant.databinding.ActivityViewpager2Binding

class AppfeaturesPager : AppCompatActivity() {

    private lateinit var binding: ActivityViewpager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpager2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager: ViewPager2 = binding.viewPagerLayout

        val pageFragments: ArrayList<Fragment> = arrayListOf(
                Features1(),
                Features2()
        )

        val adapter = AppfeaturesPagerAdapter(pageFragments, this)
        viewPager.adapter = adapter

    }
}