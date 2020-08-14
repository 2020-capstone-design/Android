package com.example.project2.bottomnavigation.recommend

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project2.R
import kotlinx.android.synthetic.main.activity_recommend.*
import java.time.LocalDate


class Recommend: Fragment() {

    var univName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_recommend, container, false)

        load()

        val recommand_button: Button = view.findViewById(R.id.recommand_button)

        val menu = resources.getStringArray(R.array.recommend_menu)
        var position = (0..menu.size-1).shuffled().last()

        var menuText: TextView = view.findViewById(R.id.menu)
        menuText.setText(menu[position])

        recommand_button.setOnClickListener {
            val menu = resources.getStringArray(R.array.recommend_menu)
            var position = (0..menu.size-1).shuffled().last()

            var menuText: TextView = view.findViewById(R.id.menu)
            menuText.setText(menu[position])
        }


        var univText: TextView = view.findViewById(R.id.univ_name)
        univText.setText(univName)


        return view
    }

    fun load() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this.context)
        val universal = pref.getString("UniversalName", null)

        println("================================ $universal")

        if (universal != null) {
            univName = universal
        }
    }

}