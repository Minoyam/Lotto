package com.cnm.lotto.network.lotto

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoApi {
    @GET("/api/v1/lotto")
    fun getLotto(
        @Query("yyyyMMdd") query: Int
    ): Observable<LottoResponse>

}