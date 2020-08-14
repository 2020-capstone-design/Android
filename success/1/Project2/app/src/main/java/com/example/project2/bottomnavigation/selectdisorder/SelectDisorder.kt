package com.example.project2.bottomnavigation.selectdisorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.project2.R
import com.example.project2.bottomnavigation.selectdisorder.first.SelectFirstFragment

class SelectDisorder: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_select_disorder, container, false)

        val startButton : Button = view.findViewById(R.id.start_button)

        startButton.setOnClickListener {
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.frameLayout,
                SelectFirstFragment()
                ).commitAllowingStateLoss()
        }
        return view
    }

}