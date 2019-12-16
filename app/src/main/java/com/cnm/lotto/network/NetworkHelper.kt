package com.cnm.lotto.network

import com.cnm.lotto.network.lotto.LottoApi
import com.cnm.lotto.network.saju.SajuApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.geniecontents.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val lottoApi = retrofit.create(LottoApi::class.java)
    val sajuApi = retrofit.create(SajuApi::class.java)
}