package com.example.weatherforecastapp.domain.ui.detail

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.databinding.ItemDayBinding
import com.example.weatherforecastapp.domain.models.Main
import com.example.weatherforecastapp.domain.models.WeatherForescast

class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder>()  {

    private lateinit var binding: FragmentHomeBinding

    var forecasts: List<WeatherForescast> = emptyList()
        set(value){
            field=value
            notifyDataSetChanged()
        }
//    var forecasts: List<WeatherForescast>=emptyList()
//            set(value){
//                field=value
//                notifyDataSetChanged()
//            }


    inner class ViewHolder(val binding:ItemDayBinding ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)    }

    override fun getItemCount(): Int =forecasts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val forecast=forecasts[position]
        with(holder.binding){
            day.text=forecast.name
            minTemp.text=forecast.timezone.toString()

        }
    }

    private fun setUpInterface(response: Pair<WeatherForescast,Main >){
        val weather=response.second
        val currentWeather=response.second.temp
        val city=response.first

        with(binding){
            temperature.text=getString(
                R.string.temperature_value,
                currentWeather.temp.toInt(),
                get
            )
        }
    }

}