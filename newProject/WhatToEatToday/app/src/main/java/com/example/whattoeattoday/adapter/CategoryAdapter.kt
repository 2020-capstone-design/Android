package com.example.whattoeattoday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whattoeattoday.menu.fragment.CategoryList

class CategoryAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return CategoryList(when(position){
            0 -> "한식"
            1 -> "분식"
            2 -> "중국집"
            3 -> "일식/돈까스"
            4 -> "치킨"
            5 -> "피자"
            6 -> "찜/탕"
            7 -> "족발/보쌈"
            8 -> "패스트푸드"
            else -> "카페/디저트"
        })
//        return when(position) {
//            0 -> {
//                KoreanFood(0)
//            }
//            1 -> {
//                KoreanFood(1)
////                SnackBar()
//            }
//            2 -> {
//                ChineseFood()
//            }
//            3 -> {
//                JapaneseFood()
//            }
//            4 -> {
//                Chicken()
//            }
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
//            else -> {
//                CafeDessert()
//            }
//
//        }
    }

    override fun getCount(): Int {
        return 10
    }

}