package com.example.whattoeattoday.menu.fragment

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.whattoeattoday.R
import com.example.whattoeattoday.adapter.OptionAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_category_item.*
import kotlinx.android.synthetic.main.activity_category_item.tab_layout
import kotlinx.android.synthetic.main.activity_rest_information.*
import kotlinx.android.synthetic.main.category_tab.view.*
import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout

class CategoryRestaurantInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_information)

        val restNum = intent.getStringExtra("restNum")
        val restName = intent.getStringExtra("restName")
        val restPhoneNumber = intent.getStringExtra("restPhoneNumber")

        setTitle(restName)
        floatingButton(restPhoneNumber)

        val fragmentAdapter = OptionAdapter(supportFragmentManager, restNum)
        menu_scroll_view.adapter = fragmentAdapter

        setOptionTab()
    }

    private fun setOptionTab() {
        val optionName = resources.getStringArray(R.array.option)

        for (optionNum in 0..1) {
            tab_layout.addTab(
                tab_layout.newTab().setCustomView(createTabView(optionName[optionNum]))
            )
        }

        val listener = TabLayout.TabLayoutOnPageChangeListener(tab_layout)
        menu_scroll_view.addOnPageChangeListener(listener)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(position: TabLayout.Tab?) {

            }

            override fun onTabUnselected(position: TabLayout.Tab?) {

            }

            override fun onTabSelected(position: TabLayout.Tab?) {
                if (position != null) {
                    menu_scroll_view.currentItem = position.position
                }
            }
        })

    }

    private fun setTitle(restName: String?) {
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = restName
    }

    private fun floatingButton(restPhoneNumber: String?) {
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${restPhoneNumber}")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    private fun createTabView(tabName: String): View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.category_tab, null)
        tabView.tab_text.text = tabName
        return tabView
    }

}