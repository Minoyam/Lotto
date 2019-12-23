package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.BallItem
import com.cnm.lotto.DateBase
import com.cnm.lotto.R
import com.cnm.lotto.network.NetworkHelper
import com.cnm.lotto.network.lotto.LottoResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_random.iv_ball_1
import kotlinx.android.synthetic.main.fragment_random.iv_ball_2
import kotlinx.android.synthetic.main.fragment_random.iv_ball_3
import kotlinx.android.synthetic.main.fragment_random.iv_ball_4
import kotlinx.android.synthetic.main.fragment_random.iv_ball_5
import kotlinx.android.synthetic.main.fragment_random.iv_ball_6
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment(), DateBase {

    private val nowDate = getDate()
    private var date = 0
    private val disposable = CompositeDisposable()
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

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun winningNumberCheck(date: Int) {
        disposable.add(
            NetworkHelper.lottoApi.getLotto(date).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe {
                    hideLoading()
                    setLottoList(it.body)
                }

        )
    }

    private fun setLottoList(body: LottoResponse.Body) {
        val ballItem = mutableListOf<BallItem>()
        ballItem.addAll(BallItem.values())
        tv_draw_date.text = "${body.drawDate} (${body.drawNo})"
        iv_ball_1.setImageResource(ballItem[body.num1 - 1].ImgResId)
        iv_ball_2.setImageResource(ballItem[body.num2 - 1].ImgResId)
        iv_ball_3.setImageResource(ballItem[body.num3 - 1].ImgResId)
        iv_ball_4.setImageResource(ballItem[body.num4 - 1].ImgResId)
        iv_ball_5.setImageResource(ballItem[body.num5 - 1].ImgResId)
        iv_ball_6.setImageResource(ballItem[body.num6 - 1].ImgResId)
        iv_ball_bonus.setImageResource(ballItem[body.bonusNum - 1].ImgResId)

    }

    private fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
    }
}