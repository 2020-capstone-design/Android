package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_select_univ.*
import org.jetbrains.anko.startActivity

class SelectUniv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_univ)

        var selectUnivNum: Int = 0
        var selectUnivName: String = ""

        val univList = resources.getStringArray(R.array.univ)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, univList)
        spinner.adapter = myAdapter

        load()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectUnivName = parent.getItemAtPosition(position).toString()
                selectUnivNum = parent.getItemIdAtPosition(position).toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        button1.setOnClickListener{
            save(selectUnivNum, selectUnivName)
            startActivity<MainActivity>()
        }

    }

    fun save(universalNum: Int, universalName: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val edit = pref.edit()
        // put

        edit.apply{
            putInt("UniversalNum", universalNum)
            putString("UniversalName", universalName)
            apply()
        }

//        edit.putInt("UniversalNum", universalNum)
//        edit.putString("UniversalName", universalName)
//        edit.apply()
    }

    fun load() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val universal = pref.getInt("UniversalNum", 0)

        if (universal != 0) {
            spinner.setSelection(universal)
        }
    }
}