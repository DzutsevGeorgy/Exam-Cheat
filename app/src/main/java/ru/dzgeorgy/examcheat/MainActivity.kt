package ru.dzgeorgy.examcheat

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.KeyEvent
import androidx.legacy.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

const val PAGES = 2

class MainActivity : WearableActivity() {
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = ScreenSlideAdapter(fragmentManager)
        pager.adapter = pagerAdapter
        
        // Enables Always-on
        setAmbientEnabled()
    }

    @Suppress("DEPRECATION")
    inner class ScreenSlideAdapter(fm: android.app.FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): android.app.Fragment {
            val fragment = ButtonsFragment()
            val bundle = Bundle()
            bundle.putInt("info", position)
            fragment.arguments = bundle
            return fragment
        }

        override fun getCount(): Int {
            return PAGES
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_STEM_1)
            fragmentManager!!.popBackStackImmediate()
        return super.onKeyDown(keyCode, event)
    }

}
