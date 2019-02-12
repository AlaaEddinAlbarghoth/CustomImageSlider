package com.alaaeddin.customimageslider.ui.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.alaaeddin.customimageslider.R

class SlideAdapter(private var imgList: ArrayList<Int>, var context: Context) : PagerAdapter() {
    var customPosition: Int = 0

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (customPosition>4)
            customPosition =0

        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.fragment_slide, container, false)
        val imageView: ImageView = view.findViewById(R.id.iv_slide)
        imageView.setImageResource(imgList[customPosition++])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == (`object` as LinearLayout)
    }
}