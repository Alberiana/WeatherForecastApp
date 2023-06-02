package com.example.weatherforecastapp.domain.forecastModel


import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<DT>,
    @SerializedName("message")
    val message: Int
)