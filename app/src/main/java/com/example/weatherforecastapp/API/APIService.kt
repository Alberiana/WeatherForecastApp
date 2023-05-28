package com.example.weatherforecastapp.API

import com.example.weatherforecastapp.domain.models.WeatherForescast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("3.0/onecall")
// getCharacters(@Query("page") page: Int): This method is annotated with @GET("character") and defines a GET request to
// retrieve a list of characters. It expects a query parameter called "page" to specify the page number of results to fetch.
// It returns a CharactersResponse object.
    suspend fun getForecasts(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appid: String,
        @Query("exclude") exclude: String?): Response<WeatherForescast>

}