package com.example.whattoeattoday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whattoeattoday.menu.fragment.category.*

class CategoryAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                KoreanFood()
            }
            1 -> {
                SnackBar()
            }
            2 -> {
                ChineseFood()
            }
            3 -> {
                JapaneseFood()
            }
            4 -> {
                Chicken()
            }
//            5 -> {
//                Pizza()
//            }
//            6 -> {
//                SteamedTang()
//            }
//            7 -> {
//                JokbalBossam()
//            }
//            8 -> {
//                FastFood()
//            }
            else -> {
                CafeDessert()
            }

        }
    }

    override fun getCount(): Int {
        return 10
    }

}