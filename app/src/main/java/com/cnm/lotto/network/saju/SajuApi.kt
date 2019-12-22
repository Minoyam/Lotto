package com.cnm.lotto.network.saju

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SajuApi {
    @GET("/fortune/internal/v1/daily")
    fun getSaju(
        @Query("targetYear") year: Int,
        @Query("targetMonth") month: Int,
        @Query("targetDay") day: Int,
        @Query("animal") animal: Int
    ): Observable<SajuResponse>
}