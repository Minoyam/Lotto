package com.cnm.lotto.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.cnm.lotto.*
import com.cnm.lotto.network.NetworkHelper
import com.cnm.lotto.network.saju.SajuResponse
import kotlinx.android.synthetic.main.fragment_random.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RandomFragment : Fragment(), DateBase {

    private var animal = mutableListOf<String>()
    private var animal_index = 1
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
        vp_saju.adapter = FragmentAdapter(fragmentManager as FragmentManager)
        itemSelected()
        sajuCreate()

        vp_saju.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {}
        })

        bt_create.setOnClickListener {
            lottoCreate()
        }

    }

    private fun sajuCreate() {
        NetworkHelper.sajuApi.getSaju(getYear(), getMonth(), getDay(), animal_index).enqueue(
            object : Callback<SajuResponse> {
                override fun onFailure(call: Call<SajuResponse>, t: Throwable) {
                    Log.e("saju", "$t")
                }

                override fun onResponse(
                    call: Call<SajuResponse>,
                    response: Response<SajuResponse>
                ) {
                    val body = response.body()
                    if (body != null) {
                        animal.add(body.summary)
                        Log.e("a", "{$animal_index},{${animal.size}}")
                        Log.e("saju", animal.toString())
                        (activity as? MainActivity)?.sajuSummary(body.summary)
                        animal_index++
                    }
                }
            }
        )
    }

    private fun lottoCreate() {
        val ballItem = BallItem.values().toList().shuffled()
        iv_ball_1.setImageResource(ballItem[0].ballImgResId)
        iv_ball_2.setImageResource(ballItem[1].ballImgResId)
        iv_ball_3.setImageResource(ballItem[2].ballImgResId)
        iv_ball_4.setImageResource(ballItem[3].ballImgResId)
        iv_ball_5.setImageResource(ballItem[4].ballImgResId)
        iv_ball_6.setImageResource(ballItem[5].ballImgResId)
    }

    private fun itemSelected(): Boolean {
        fragmentManager!!.beginTransaction()
            .replace(R.id.fl_saju, sajuFragment, "SAJU")
            .commit()
        return true
    }
}