package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.project2.adapter.CategoryAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_category_item.*
import kotlinx.android.synthetic.main.custom_tab.view.*

// 탭 창.
class CategoryItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_item)

        // list_viewpager와 연결

        val fragmentAdapter = CategoryAdapter(supportFragmentManager)
        rest_list_view.adapter = fragmentAdapter

        // 카테고리 이름 매칭
        val name = resources.getStringArray(R.array.category_name)

        for (categoryNum in 0..9) {
            tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView(name[categoryNum])))
        }

        val listener = TabLayout.TabLayoutOnPageChangeListener(tab_layout)

        rest_list_view.addOnPageChangeListener(listener)


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    rest_list_view.currentItem = p0.position
                }
            }

        })


        listener.onPageSelected(intent.getIntExtra("category", 0))

    }

    // custom_tab.xml의 tab 부분에 아이템들을 하나씩 넣어준다. 즉, 탭 레이아웃 안에 커스텀 탭들을 하나씩 넣어줌
    private fun createTabView(tabName: String): View {
        var tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.category_name_text.text = tabName
        return tabView
    }

}