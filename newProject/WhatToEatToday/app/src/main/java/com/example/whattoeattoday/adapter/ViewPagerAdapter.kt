package com.example.whattoeattoday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.whattoeattoday.R

class ViewPagerAdapter(private val context: Context?): PagerAdapter() {

    lateinit var layoutInflater : LayoutInflater

    val Image = arrayOf(
        R.drawable.recommand_soup,
        R.drawable.recommand_sushi,
        R.drawable.recommand_tteokbokki
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        // 슬라이드가 몇개 넘어갈지 크기를 지정해준다.
        return Image.size
    }

    // 이미지 생성하는 부분과 이미지가 없어지는 부분

    // position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (context != null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        val v = layoutInflater.inflate(R.layout.viewpager_activity, null)
        val image = v.findViewById<View>(R.id.imageview) as ImageView

        image.setImageResource(Image[position])

        val vp = container as ViewPager
        vp.addView(v, 0)

        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }

}