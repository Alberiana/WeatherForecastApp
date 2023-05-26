package com.example.weatherforecastapp.API

import com.example.weatherforecastapp.models.WeatherForescast
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("character")
// getCharacters(@Query("page") page: Int): This method is annotated with @GET("character") and defines a GET request to
// retrieve a list of characters. It expects a query parameter called "page" to specify the page number of results to fetch.
// It returns a CharactersResponse object.
    suspend fun getCharacters(@Query("page") page: Int): WeatherForescast

}