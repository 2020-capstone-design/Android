package com.example.whattoeattoday.menu.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.whattoeattoday.R

class Recommand : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_recommend, container, false)

        val menuText = resources.getStringArray(R.array.recommend_menu)
        val menuImage = resources.obtainTypedArray(R.array.recommend_menu_img)
        val position = (0..menuText.size-1).shuffled().last()


        val menuTextView: TextView = view.findViewById(R.id.menu)
        menuTextView.setText(menuText[position])

        val menuImageView: ImageView = view.findViewById(R.id.recommend_food_img)
        menuImageView.setImageResource(menuImage.getResourceId(position, -1))

        val recommendListView: Button = view.findViewById(R.id.recommend_list)
        recommendListView.setOnClickListener {
            val intent = Intent(activity, RecommandResult::class.java)
            startActivity(intent)
        }

        return view
    }

}

