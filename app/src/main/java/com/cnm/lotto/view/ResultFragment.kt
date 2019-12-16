package com.cnm.lotto.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.BallItem
import com.cnm.lotto.DateBase
import com.cnm.lotto.R
import com.cnm.lotto.network.NetworkHelper
import com.cnm.lotto.network.lotto.LottoResponse
import kotlinx.android.synthetic.main.fragment_random.iv_ball_1
import kotlinx.android.synthetic.main.fragment_random.iv_ball_2
import kotlinx.android.synthetic.main.fragment_random.iv_ball_3
import kotlinx.android.synthetic.main.fragment_random.iv_ball_4
import kotlinx.android.synthetic.main.fragment_random.iv_ball_5
import kotlinx.android.synthetic.main.fragment_random.iv_ball_6
import kotlinx.android.synthetic.main.fragment_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultFragment : Fragment(), DateBase {

    private val nowDate = getDate()
    private var date = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (date == 0) {
            date = nowDate
            winningNumberCheck(date)
        }
        ib_back.setOnClickListener {
            showLoading()
            date -= 7
            winningNumberCheck(date)
        }
        ib_forward.setOnClickListener {
            showLoading()
            if (date != nowDate) {
                date += 7
                winningNumberCheck(date)
            }
        }
    }

    private fun winningNumberCheck(date: Int) {
        NetworkHelper.lottoApi.getLotto(date).enqueue(
            object : Callback<LottoResponse> {
                override fun onFailure(call: Call<LottoResponse>, t: Throwable) {
                    Log.e("Network", "$t")
                }

                override fun onResponse(
                    call: Call<LottoResponse>,
                    response: Response<LottoResponse>
                ) {
                    hideLoading()
                    val body = response.body()
                    if (body != null) {
                        setLottoList(body.body)
                    }
                }
            }
        )
    }

    private fun setLottoList(body: LottoResponse.Body) {
        val ballItem = mutableListOf<BallItem>()
        ballItem.addAll(BallItem.values())
        tv_draw_date.text = "${body.drawDate} (${body.drawNo})"
        iv_ball_1.setImageResource(ballItem[body.num1 - 1].ballImgResId)
        iv_ball_2.setImageResource(ballItem[body.num2 - 1].ballImgResId)
        iv_ball_3.setImageResource(ballItem[body.num3 - 1].ballImgResId)
        iv_ball_4.setImageResource(ballItem[body.num4 - 1].ballImgResId)
        iv_ball_5.setImageResource(ballItem[body.num5 - 1].ballImgResId)
        iv_ball_6.setImageResource(ballItem[body.num6 - 1].ballImgResId)
        iv_ball_bonus.setImageResource(ballItem[body.bonusNum - 1].ballImgResId)

    }

    private fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
    }
}