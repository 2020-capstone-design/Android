package com.example.whattoeattoday.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.whattoeattoday.R

class SelectThird(val num: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_first, container, false)

        val select1 : ImageView = view.findViewById(R.id.select1)
        val select2 : ImageView = view.findViewById(R.id.select2)

        val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()


        when(num) {
            0 -> {
                select1.setImageResource(R.drawable.red)
                select2.setImageResource(R.drawable.white)

                select1.setOnClickListener {
                    transaction.replace(R.id.frameLayout,
                        SelectResult(0)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(1)
                    ).commitAllowingStateLoss()
                }
            }
            1 -> {
                select1.setImageResource(R.drawable.beef)
                select2.setImageResource(R.drawable.notbeef)

                select1.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(2)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(3)
                    ).commitAllowingStateLoss()
                }
            }
            2 -> {
                select1.setImageResource(R.drawable.warm)
                select2.setImageResource(R.drawable.cool)

                select1.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(4)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(5)
                    ).commitAllowingStateLoss()
                }
            }
            3 -> {
                select1.setImageResource(R.drawable.noodle)
                select2.setImageResource(R.drawable.notnoodle)

                select1.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(6)
                    ).commitAllowingStateLoss()
                }

                select2.setOnClickListener {
//                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    transaction.replace(R.id.frameLayout,
                        SelectResult(7)
                    ).commitAllowingStateLoss()
                }
            }
        }

        return view
    }

}