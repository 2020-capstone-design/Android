package com.example.whattoeattoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.whattoeattoday.menu.fragment.Category
import com.example.whattoeattoday.menu.fragment.Recommend
import com.example.whattoeattoday.menu.fragment.Roulette
import com.example.whattoeattoday.menu.fragment.SelectDisorder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val fragmentManager: FragmentManager = supportFragmentManager

    // 첫 번째 뒤로가기 클릭과 두 번째 뒤로가기 버튼의 시간 차.
    private var first_time : Long = 0
    private var second_time : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,
            Recommend()
        ).commitAllowingStateLoss()

        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()

        when(menuItem.itemId) {
            R.id.recommend -> transaction.replace(R.id.frameLayout,
                Recommend()
            ).commitAllowingStateLoss()
            R.id.category -> transaction.replace(R.id.frameLayout,
                Category()
            ).commitAllowingStateLoss()
            R.id.select_disorder -> transaction.replace(R.id.frameLayout,
                SelectDisorder()
            ).commitAllowingStateLoss()
            else -> transaction.replace(R.id.frameLayout,
                Roulette()
            ).commitAllowingStateLoss()
        }
        return true
    }

    // actionbar에 location icon 추가.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.location_menu, menu)

        return true;
    }

    // location icon 버튼이 눌렸을 시 엑티비티 전환
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.location -> {
                startActivity<SelectUniv>()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        second_time = System.currentTimeMillis()
        if(second_time - first_time < 2000){
            super.onBackPressed()
            finish()
        }else toast("'뒤로' 버튼을 한 번 더 누르면 종료됩니다.")
        first_time = System.currentTimeMillis()
    }

}