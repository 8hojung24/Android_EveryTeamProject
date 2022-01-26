package com.example.everyteamproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MyAdapter(fa: FragmentActivity?, var mCount: Int) : FragmentStateAdapter(fa!!) {
    override fun createFragment(position: Int): Fragment {
        val index = getRealPosition(position)
        if (index == 0) return Fragment_one()
        else if (index == 1) return Fragment_two()
        else if (index == 2) return Fragment_three()
        else if (index == 3) return Fragment_three()
        else if (index == 4) return Fragment_four()
        else if (index == 5) return Fragment_five()
        else return Fragment_six()
    }

    override fun getItemCount(): Int {
        return 2000
    }

    fun getRealPosition(position: Int): Int {
        return position % mCount
    }
}