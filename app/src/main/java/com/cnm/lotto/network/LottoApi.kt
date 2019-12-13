package com.cnm.lotto.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {
    @GET("/api/v1/lotto")
    fun getLotto(
        @Query("yyyyMMdd") query: Int
    ): Call<LottoResponse>

}