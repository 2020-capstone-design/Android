package com.example.project2.bottomnavigation.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project2.R
import com.example.project2.bottomnavigation.category.fragment.restinfo.InfoFragment
import com.example.project2.bottomnavigation.category.fragment.restinfo.MenuFragment
import kotlinx.android.synthetic.main.activity_rest_info.*

class RestInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_info)

        var restNum = intent.getStringExtra("restNum")
        var restName = intent.getStringExtra("restName")

        rest_name_text.setText(restName)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area, MenuFragment(restNum))
            .commit()

        menu.setOnClickListener {
            // 여기서 메뉴 버튼이 눌렸을때, 메뉴의 버튼 배경 색이나, 글씨 크기 조절할 수 있음.
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, MenuFragment(restNum))
                .commit()
        }

        info.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, InfoFragment())
                .commit()
        }

    }
}