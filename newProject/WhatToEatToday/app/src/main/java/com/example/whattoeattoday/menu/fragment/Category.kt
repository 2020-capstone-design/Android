package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.GridviewAdapter
import com.example.whattoeattoday.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.support.v4.startActivity

class Category() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ViewPagerAdapter 연결 부분
        val adapter =
            ViewPagerAdapter(this.context!!.applicationContext)
        viewpager.adapter = adapter

        // GirdviewAdapter 연결 부분
        val img = resources.obtainTypedArray(R.array.category_img)
        val name = resources.getStringArray(R.array.category_name)

        val gridviewAdapter = GridviewAdapter(this.context!!.applicationContext, img, name)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{ adapterView, view, i, l ->

            startActivity<CategoryItem>(
                "category" to i
            )

        }
    }

}
