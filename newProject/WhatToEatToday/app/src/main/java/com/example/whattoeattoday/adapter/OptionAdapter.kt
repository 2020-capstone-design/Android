package com.example.whattoeattoday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whattoeattoday.menu.fragment.RecommandRestaurantInformation
import com.example.whattoeattoday.menu.fragment.RestaurantMenu

class OptionAdapter (fm: FragmentManager, val restNum : String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                RestaurantMenu(restNum)
            }
            else -> {
                RecommandRestaurantInformation(restNum)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }


}
