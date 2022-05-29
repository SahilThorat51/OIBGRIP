package com.phani.unitconverter.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.phani.unitconverter.LengthFragment
import com.phani.unitconverter.TemperatureFragment
import com.phani.unitconverter.WeightFragment

class PagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LengthFragment()
            1 -> TemperatureFragment()
            2 -> WeightFragment()
            else -> throw Resources.NotFoundException("Position Not Found")
        }
    }
}