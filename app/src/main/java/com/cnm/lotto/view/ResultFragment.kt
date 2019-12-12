package com.cnm.lotto.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.R
import com.cnm.lotto.network.NetworkHelper
import java.text.SimpleDateFormat
import java.util.*

class ResultFragment : Fragment() {

    private val date = Date(System.currentTimeMillis())
    private val simpleDate = SimpleDateFormat("yyyyMMdd")
    private val data_1 =simpleDate.format(date).toInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        NetworkHelper.lottoApi.getLotto()
        Log.e("date","$data_1")
    }
}