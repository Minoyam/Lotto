package com.cnm.lotto.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {
    @GET("v1/lotto")
    fun getLotto(
        @Query("query") query: String
    ): Call<LottoResponse>

}