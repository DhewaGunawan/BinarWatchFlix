package com.example.binarwatchflix.pkg.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binarwatchflix.constant.ShowConstant
import com.example.binarwatchflix.pkg.home.ui.homelist.HomeListFragment

class HomeViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeListFragment(ShowConstant.MOVIES)
            1 -> fragment = HomeListFragment(ShowConstant.TV_SHOW)
        }
        return fragment as Fragment
    }
}
