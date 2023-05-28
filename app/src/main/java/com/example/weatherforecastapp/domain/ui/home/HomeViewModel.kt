package com.example.weatherforecastapp.domain.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domain.models.WeatherForescast
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val forecastList=ArrayList<WeatherForescast>()
    private val forecastRepo= ArrayList<WeatherForescast>()
    val forecastLiveData = MutableLiveData<List<WeatherForescast>>()


    fun getForecasts(day: Int){
        viewModelScope.launch {
            forecastList.addAll(forecastRepo.get(day).toString())
        }
    }
}