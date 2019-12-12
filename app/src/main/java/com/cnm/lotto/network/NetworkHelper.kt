package com.cnm.lotto.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.geniecontents.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val lottoApi = retrofit.create(LottoApi::class.java)
}