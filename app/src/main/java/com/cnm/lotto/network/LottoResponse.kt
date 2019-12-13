package com.cnm.lotto.network


import com.google.gson.annotations.SerializedName

data class LottoResponse(
    @SerializedName("body")
    val body: Body,
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("statusCode")
    val statusCode: String
) {
    data class Body(
        @SerializedName("bonusNum")
        val bonusNum: Int,
        @SerializedName("drawDate")
        val drawDate: String,
        @SerializedName("drawDateYn")
        val drawDateYn: String,
        @SerializedName("drawNo")
        val drawNo: Int,
        @SerializedName("lottoResult")
        val lottoResult: List<LottoResult>,
        @SerializedName("num1")
        val num1: Int,
        @SerializedName("num2")
        val num2: Int,
        @SerializedName("num3")
        val num3: Int,
        @SerializedName("num4")
        val num4: Int,
        @SerializedName("num5")
        val num5: Int,
        @SerializedName("num6")
        val num6: Int,
        @SerializedName("totalSellingPrice")
        val totalSellingPrice: Long
    ) {
        data class LottoResult(
            @SerializedName("rank")
            val rank: String,
            @SerializedName("sellingPriceByRank")
            val sellingPriceByRank: Long,
            @SerializedName("winningCnt")
            val winningCnt: Int,
            @SerializedName("winningPriceByRank")
            val winningPriceByRank: Long
        )
    }
}