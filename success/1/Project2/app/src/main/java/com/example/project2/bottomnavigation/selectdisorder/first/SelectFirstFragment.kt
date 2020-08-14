package com.example.project2.bottomnavigation.selectdisorder.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.project2.R
import com.example.project2.bottomnavigation.selectdisorder.second.NotRiceFragment
import com.example.project2.bottomnavigation.selectdisorder.second.RiceFragment

class SelectFirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select, container, false)

        val select1 : ImageView = view.findViewById(R.id.select1)
        val select2 : ImageView = view.findViewById(R.id.select2)

        select1.setOnClickListener {
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
            transaction.replace(R.id.frameLayout,
                RiceFragment()
            ).commitAllowingStateLoss()
        }


        select2.setOnClickListener {
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.frameLayout,
                NotRiceFragment()
            ).commitAllowingStateLoss()
        }

        return view
    }

}