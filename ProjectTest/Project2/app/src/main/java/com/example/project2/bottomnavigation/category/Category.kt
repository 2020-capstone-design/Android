package com.example.project2.bottomnavigation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project2.LectureActivity
import com.example.project2.adapter.GridviewAdapter
import com.example.project2.R
import com.example.project2.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_japanese_food.*
import org.jetbrains.anko.sdk27.coroutines.onClick
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
        // ViewPagerAdapter 연결 부분ƒ
        val adapter =
            ViewPagerAdapter(this.context!!.applicationContext)
        viewpager.adapter = adapter

        // GirdviewAdapter 연결 부분
        val img = resources.obtainTypedArray(R.array.category_img)
        val name = resources.getStringArray(R.array.category_name)

        val gridviewAdapter = GridviewAdapter(this.context!!.applicationContext, img, name)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{ adapterView, view, i, l ->

//            startActivity<LectureActivity>()

            println("================" + i)

            startActivity<LectureActivity>(
                "category" to i
            )

//            if(i == null) {
//                print("asdfsafdklsfklskljdfklsjfl=================================================== bykadfasdfasfdsdfasf" + i)
//
//            }
//
//            print("asdfsafdklsfklskljdfklsjfl===================================================" + i)
//            startActivity<LectureActivity>(
//                "category" to i
//            )
        }
    }

}