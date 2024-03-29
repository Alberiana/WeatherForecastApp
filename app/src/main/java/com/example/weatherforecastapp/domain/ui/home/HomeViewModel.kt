package com.example.weatherforecastapp.domain.ui.home


import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.domain.forecastModel.ForecastModel
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.domain.repo.CurrentWeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var currentPage = 1
    private val weatherRepo = CurrentWeatherRepository()
    private val weatherList = ArrayList<WeatherModel>()
    private val search = ArrayList<WeatherModel>()
    val weatherLiveData = MutableLiveData<List<WeatherModel>>()
    val forecastLiveData=MutableLiveData<List<ForecastModel>>()

    fun getWeatherList(q: String, appid: String) {
        viewModelScope.launch {
            try {
                val weatherData = weatherRepo.getCurrentWeatherData(q, appid)
                weatherLiveData.value = listOf(weatherData)
                Log.d("weatherdata", "Forecast data retrievedd: $weatherData")

            } catch (e: Exception) {
                Log.e("Tag", "Error fetching weather data: ${e.message}", e)
            }
        }
    }

    fun getForecastData(q: String, appid: String){
        viewModelScope.launch {
            try {
                val forecastData = weatherRepo.getForecastData(q, appid)
                forecastLiveData.value = listOf(forecastData)
                Log.d("HomeViewModel", "Forecast data retrieved: $forecastData")

            } catch (e: Exception) {
                Log.e("Tag", "Error fetching weather data: ${e.message}", e)
            }
        }
    }

//    fun searchCity(query: String) {
//      search.clear()
//        for (weather in weatherList){
//            weather.name?.let{
//                if(it.startsWith(query,true)){
//                    search.add(weather)
//                }
//            }
//        }
//        weatherLiveData.value=search
//    }

}
