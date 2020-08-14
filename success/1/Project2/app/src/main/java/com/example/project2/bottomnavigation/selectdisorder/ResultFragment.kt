package com.example.project2.bottomnavigation.selectdisorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project2.R
import java.util.*

private const val ARG_PARAM1 = "param1"

class ResultFragment(val num : Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = when(num) {
            0 -> resources.getStringArray(R.array.select_desorder_result_0)
            1 -> resources.getStringArray(R.array.select_desorder_result_1)
            2 -> resources.getStringArray(R.array.select_desorder_result_2)
            3 -> resources.getStringArray(R.array.select_desorder_result_3)
            4 -> resources.getStringArray(R.array.select_desorder_result_4)
            5 -> resources.getStringArray(R.array.select_desorder_result_5)
            6 -> resources.getStringArray(R.array.select_desorder_result_6)
            else -> resources.getStringArray(R.array.select_desorder_result_7)
        }

        val text : TextView = view.findViewById(R.id.text)
        val random = Random().nextInt(name.size)

        text.setText(name[random])

    }


}