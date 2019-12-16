package com.cnm.lotto.network.saju


import com.google.gson.annotations.SerializedName

data class SajuResponse(
    @SerializedName("animal")
    val animal: String,
    @SerializedName("animalImgUrl")
    val animalImgUrl: String,
    @SerializedName("fType")
    val fType: String,
    @SerializedName("list")
    val list: List<X>,
    @SerializedName("startTimePeriod")
    val startTimePeriod: String,
    @SerializedName("summary")
    val summary: String
) {
    data class X(
        @SerializedName("description")
        val description: String,
        @SerializedName("year")
        val year: String
    )
}