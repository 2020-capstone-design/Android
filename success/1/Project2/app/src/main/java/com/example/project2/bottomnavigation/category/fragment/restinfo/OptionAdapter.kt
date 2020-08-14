package com.example.project2.bottomnavigation.category.fragment.restinfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OptionAdapter (fm: FragmentManager, val restNum : String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                MenuFragment(restNum)
            }
            else -> {
                InfoFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }


}
