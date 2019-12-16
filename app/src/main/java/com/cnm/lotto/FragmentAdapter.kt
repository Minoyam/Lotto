package com.cnm.lotto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cnm.lotto.view.SajuFragment

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{

    private var sajuList = mutableListOf<Fragment>()


    override fun getItem(position: Int): Fragment {
        return when(position){
        0 -> SajuFragment()
            else -> error("")
        }
    }

    override fun getCount(): Int {
        return sajuList.size
    }

}