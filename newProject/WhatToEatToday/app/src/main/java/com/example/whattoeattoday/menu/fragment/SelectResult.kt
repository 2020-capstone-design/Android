package com.example.whattoeattoday.menu.fragment

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.whattoeattoday.R
import java.util.*

class SelectResult(val num: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_result, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: Array<String>
        val image: TypedArray

        when (num) {
            0 -> {
                name = resources.getStringArray(R.array.select_desorder_result_0)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_0)
            }
            1 -> {
                name = resources.getStringArray(R.array.select_desorder_result_1)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_1)
            }
            2 -> {
                name = resources.getStringArray(R.array.select_desorder_result_2)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_2)
            }
            3 -> {
                name = resources.getStringArray(R.array.select_desorder_result_3)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_3)
            }
            4 -> {
                name = resources.getStringArray(R.array.select_desorder_result_4)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_4)
            }
            5 -> {
                name = resources.getStringArray(R.array.select_desorder_result_5)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_5)
            }
            6 -> {
                name = resources.getStringArray(R.array.select_desorder_result_6)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_6)
            }
            else -> {
                name = resources.getStringArray(R.array.select_desorder_result_7)
                image = resources.obtainTypedArray(R.array.select_desorder_result_image_7)
            }
        }



        val menuTextView: TextView = view.findViewById(R.id.menu)
        val randomPosition = Random().nextInt(name.size)

        menuTextView.text = name[randomPosition]

        val menuImageView: ImageView = view.findViewById(R.id.recommend_food_img)
        menuImageView.setImageResource(image.getResourceId(randomPosition, -1))

        val recommendListView: Button = view.findViewById(R.id.select_disorder_list)
        recommendListView.setOnClickListener {
            val intent = Intent(activity, RecommandResult::class.java)
            startActivity(intent)
        }

    }

}