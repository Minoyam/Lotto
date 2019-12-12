package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.BallItem
import com.cnm.lotto.R
import kotlinx.android.synthetic.main.fragment_random.*

class RandomFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bt_create.setOnClickListener {
            val ballItem = BallItem.values().toList().shuffled()
            iv_ball_1.setImageResource(ballItem[0].ballImgResId)
            iv_ball_2.setImageResource(ballItem[1].ballImgResId)
            iv_ball_3.setImageResource(ballItem[2].ballImgResId)
            iv_ball_4.setImageResource(ballItem[3].ballImgResId)
            iv_ball_5.setImageResource(ballItem[4].ballImgResId)
            iv_ball_6.setImageResource(ballItem[5].ballImgResId)

        }
    }
}