package jem.temidayo.myrestuarant.appIntro

import android.content.Intent
import android.os.Bundle
import android.view.View
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
            }
        }

        binding.continueText.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        registerListener()
    }


    private fun registerListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.indicatorLayout.selectCurrentPosition(position)
                if (position != fragmentList.lastIndex) {
                    binding.next.visibility = View.VISIBLE
                    binding.continueText.visibility = View.INVISIBLE
                }else{
                    binding.next.visibility = View.INVISIBLE
                    binding.continueText.visibility = View.VISIBLE
                }
            }
        })
    }
}