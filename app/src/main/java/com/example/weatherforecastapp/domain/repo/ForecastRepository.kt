package com.example.weatherforecastapp.domain.repo

import com.example.weatherforecastapp.API.APIService
import com.example.weatherforecastapp.domain.models.WeatherForescast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepository {

    private val apiService: APIService

    init{
        val retrofit=Retrofit.Builder()
            .baseUrl("https://openweathermap.org/data")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService=retrofit.create(APIService::class.java)
    }
    suspend fun getForecast(
        lat: Double,
        lon: Double,
        units: String,
        appid: String,
        exclude: String
    ) = apiService.getForecasts(lat, lon, units, appid, exclude)


//    suspend fun getForecasts(day: Int):WeatherForescast=
//        apiService.getForecasts(day)
}