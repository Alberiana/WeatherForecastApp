package com.example.weatherforecastapp.domain.ui.home


import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.domain.repo.CurrentWeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val weatherRepo = CurrentWeatherRepository()
    private val weatherList = ArrayList<WeatherModel>()
    private val search = ArrayList<WeatherModel>()
    val weatherLiveData = MutableLiveData<List<WeatherModel>>()

    fun getWeatherList(q: String, appid: String) {
        viewModelScope.launch {
            try {
                val weatherData = weatherRepo.getCurrentWeatherData(q, appid)

                // Update the weatherLiveData with the weather data
                weatherLiveData.value = listOf(weatherData)
            } catch (e: Exception) {
                Log.d("Tag","thiss is error")
                // Handle the exception appropriately
            }
        }
    }

    fun searchCity(query: String) {
      search.clear()
        for (weather in weatherList){
            weather.name?.let{
                if(it.startsWith(query,true)){
                    search.add(weather)
                }
            }
        }
        weatherLiveData.value=search
    }

}
