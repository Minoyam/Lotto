package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.data.BallItem
import com.cnm.lotto.R
import kotlinx.android.synthetic.main.fragment_random.*


class RandomFragment : Fragment() {

    private val sajuFragment = SajuFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemSelected()
        bt_create.setOnClickListener { lottoCreate() }

    }

    private fun lottoCreate() {
        val ballItem = BallItem.values().toList().shuffled()
        iv_ball_1.setImageResource(ballItem[0].ImgResId)
        iv_ball_2.setImageResource(ballItem[1].ImgResId)
        iv_ball_3.setImageResource(ballItem[2].ImgResId)
        iv_ball_4.setImageResource(ballItem[3].ImgResId)
        iv_ball_5.setImageResource(ballItem[4].ImgResId)
        iv_ball_6.setImageResource(ballItem[5].ImgResId)
    }

    private fun itemSelected(): Boolean {
        fragmentManager!!.beginTransaction()
            .replace(R.id.fl_saju, sajuFragment, "SAJU")
            .commit()
        return true
    }
}