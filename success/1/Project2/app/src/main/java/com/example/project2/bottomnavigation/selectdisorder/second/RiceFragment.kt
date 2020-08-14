package com.example.project2.bottomnavigation.selectdisorder.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.project2.R
import com.example.project2.bottomnavigation.selectdisorder.third.NotRiceBrothFragment
import com.example.project2.bottomnavigation.selectdisorder.third.NotRiceNoBrothFragment
import com.example.project2.bottomnavigation.selectdisorder.third.RiceBrothFragment
import com.example.project2.bottomnavigation.selectdisorder.third.RiceNoBrothFragment

class RiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rice, container, false)

        val select1 : ImageView = view.findViewById(R.id.select1)
        val select2 : ImageView = view.findViewById(R.id.select2)

        select1.setOnClickListener {
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.frameLayout,
                RiceBrothFragment()
            ).commitAllowingStateLoss()
        }

        select2.setOnClickListener {
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.frameLayout,
                RiceNoBrothFragment()
            ).commitAllowingStateLoss()
        }

        return view
    }

}