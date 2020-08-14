package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.project2.bottomnavigation.category.Category
import com.example.project2.bottomnavigation.recommend.Recommend
import com.example.project2.bottomnavigation.roulette.Roulette
import com.example.project2.bottomnavigation.selectdisorder.SelectDisorder
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val fragmentManager: FragmentManager = supportFragmentManager

    // 첫 번째 뒤로가기 클릭과 두 번째 뒤로가기 버튼의 시간 차.
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

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
        // 프레그먼트가 바뀔때마다 만들어져야함.
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
            }
        }
        return super.onOptionsItemSelected(item)
    }

        // 뒤로가기 버튼을 클릭했을 시,
    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            var tempTime = System.currentTimeMillis();
            var intervalTime = tempTime - backPressedTime;
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
//                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                toast("'뒤로' 버튼을 한 번 더 누르면 종료됩니다.")
                return
            }
        }
        super.onBackPressed();
    }

}