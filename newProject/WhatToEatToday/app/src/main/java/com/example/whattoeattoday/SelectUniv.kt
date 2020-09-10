package com.example.whattoeattoday

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattoeattoday.vo.SharedPreference
import kotlinx.android.synthetic.main.activity_select_univ.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SelectUniv : AppCompatActivity() {

    private var first_time : Long = 0
    private var second_time : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_univ)

        var selectUnivNum: Int = 0
        var selectUnivName: String = ""

        val univList = resources.getStringArray(R.array.univ)
        val myAdapter: ArrayAdapter<String> = object :
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, univList) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }

        spinner.adapter = myAdapter

        if (SharedPreference.universalNum != -1) {
            spinner.setSelection(SharedPreference.universalNum)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectUnivName = parent.getItemAtPosition(position).toString()
                selectUnivNum = parent.getItemIdAtPosition(position).toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        button1.setOnClickListener {
            when (selectUnivNum) {
                0 -> toast("학교를 선택해주세요")
                else -> {
                    SharedPreference.universalNum = selectUnivNum
                    SharedPreference.universalName = selectUnivName
                    startActivity<MainActivity>()
                    finish()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        toast("SelectUniv Activity onDestroy")
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