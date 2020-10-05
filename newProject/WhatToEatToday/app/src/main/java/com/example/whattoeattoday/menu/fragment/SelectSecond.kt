package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.whattoeattoday.R


class SelectSecond(val num : Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_first, container, false)

        val select1 : ImageView = view.findViewById(R.id.select1)
        val select2 : ImageView = view.findViewById(R.id.select2)

        select1.setImageResource(R.drawable.broth)
        select2.setImageResource(R.drawable.nobroth)

        val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()


        when(num) {
            0 -> {
                select1.setOnClickListener {
                    transaction.replace(R.id.frameLayout,
                        SelectThird(0)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectThird(1)
                    ).commitAllowingStateLoss()
                }
            }
            1 -> {
                select1.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectThird(2)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectThird(3)
                    ).commitAllowingStateLoss()
                }
            }
        }

        return view
    }

}