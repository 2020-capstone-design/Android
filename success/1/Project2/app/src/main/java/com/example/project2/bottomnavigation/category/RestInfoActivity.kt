package com.example.project2.bottomnavigation.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.project2.R
import com.example.project2.bottomnavigation.category.fragment.restinfo.InfoFragment
import com.example.project2.bottomnavigation.category.fragment.restinfo.MenuFragment
import com.example.project2.bottomnavigation.category.fragment.restinfo.OptionAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_category_item.*
import kotlinx.android.synthetic.main.activity_rest_info.*
import kotlinx.android.synthetic.main.activity_rest_info.tab_layout
import kotlinx.android.synthetic.main.custom_tab.view.*

class RestInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_info)

        var restNum = intent.getStringExtra("restNum")
        var restName = intent.getStringExtra("restName")


        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = restName
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val fragmentAdapter = OptionAdapter(supportFragmentManager, restNum)
        scroll_view.adapter = fragmentAdapter

        val optionName = resources.getStringArray(R.array.option)

        for(optionNum in 0..1) {
            tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView(optionName[optionNum])))
        }

        val listener = TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        scroll_view.addOnPageChangeListener(listener)

        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    scroll_view.currentItem = p0.position
                }
            }
        })



//        rest_name_text.setText(restName)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_area, MenuFragment(restNum))
//            .commit()

//        menu.setOnClickListener {
//            // 여기서 메뉴 버튼이 눌렸을때, 메뉴의 버튼 배경 색이나, 글씨 크기 조절할 수 있음.
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_area, MenuFragment(restNum))
//                .commit()
//        }
//
//        info.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_area, InfoFragment())
//                .commit()
//        }


    }

    private fun createTabView(tabName: String): View {
        var tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.tab_text.text = tabName
        return tabView
    }

}