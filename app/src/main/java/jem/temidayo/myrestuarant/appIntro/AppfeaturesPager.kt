package jem.temidayo.myrestuarant.appIntro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import jem.temidayo.myrestuarant.MainActivity
import jem.temidayo.myrestuarant.appIntro.features.Feature1
import jem.temidayo.myrestuarant.appIntro.features.Feature2
import jem.temidayo.myrestuarant.databinding.ActivityViewpager2Binding
import jem.temidayo.myrestuarant.appIntro.features.PagerAdapter

class AppfeaturesPager : AppCompatActivity() {

    private lateinit var binding: ActivityViewpager2Binding
    private val fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpager2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.viewPagerLayout

        val adapter = PagerAdapter(this)
        viewPager.adapter = adapter

        fragmentList.addAll(listOf(
                Feature1(), Feature2()
        ))

        adapter.setFragmentList(fragmentList)

        binding.indicatorLayout.setIndicatorCount(adapter.itemCount)
        binding.indicatorLayout.selectCurrentPosition(0)

        binding.next.setOnClickListener {
            val position = viewPager.currentItem
            if (position < 1) {
                viewPager.setCurrentItem(position + 1)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        registerListner()
    }

    private fun registerListner() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicatorLayout.selectCurrentPosition(position)
//                if (position < fragmentList.lastIndex) {
//                    tvSkip.visibility = View.VISIBLE
//                    tvNext.text = "Next"
//                } else {
//                    tvSkip.visibility = View.GONE
//                    tvNext.text = "Get Started"
//                }
            }
        })
    }
}