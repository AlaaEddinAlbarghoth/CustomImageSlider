package com.alaaeddin.customimageslider.ui.activities

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.alaaeddin.customimageslider.R
import com.alaaeddin.customimageslider.ui.adapters.SlideAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    /** Pager */
    private lateinit var pager: ViewPager

    /** Adapter */
    private lateinit var adapter: SlideAdapter

    /**  */
    private lateinit var timer: Timer
    private var current_position: Int = 0
    private var custom_position: Int = 0

    /** List */
    private var image_recources_list: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        this.pager = this.findViewById(R.id.pager_slider)
        this.fillData()
        this.adapter = SlideAdapter(this.image_recources_list, this)
        this.pager.adapter = this.adapter
        prepareDots(custom_position++)
        createSlideShow()

        pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(currentPostion: Int) {
                if (custom_position >image_recources_list.size)
                    custom_position = 0

                prepareDots(custom_position)
            }
        })
    }

    /**
     * @sample : This is  a method to fill data.
     *  */
    private fun fillData() {
        this.image_recources_list.add(R.drawable.stamp1)
        this.image_recources_list.add(R.drawable.stamp2)
        this.image_recources_list.add(R.drawable.stamp3)
        this.image_recources_list.add(R.drawable.stamp4)
        this.image_recources_list.add(R.drawable.stamp5)
        this.image_recources_list.add(R.drawable.stamp6)
        this.image_recources_list.add(R.drawable.stamp7)
        this.image_recources_list.add(R.drawable.stamp8)
        this.image_recources_list.add(R.drawable.stamp9)
        this.image_recources_list.add(R.drawable.stamp10)
    }

    private fun createSlideShow() {
        val handler = Handler()
        val runnable = Runnable {
            if (current_position == Int.MAX_VALUE) {
                current_position = 0
            }
            pager.setCurrentItem(current_position++, true)
        }

        timer = Timer()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 250, 3000)
    }

    private fun prepareDots(currentPostion: Int) {
        if (ll_dots_container.childCount > 0)
            ll_dots_container.removeAllViews()

        val dots: Array<ImageView> = Array(image_recources_list.size) { ImageView(this) }

        for (i in image_recources_list.indices) {
            if (i == currentPostion)
                dots[i].setImageResource(R.drawable.active_dot)
            else
                dots[i].setImageResource(R.drawable.inactive_dot)

            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)

            ll_dots_container.addView(dots[i], params)
        }
    }
}
