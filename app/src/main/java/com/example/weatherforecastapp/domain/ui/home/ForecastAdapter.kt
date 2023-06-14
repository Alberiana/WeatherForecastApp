package com.example.weatherforecastapp.domain.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.databinding.ItemDayBinding
import com.example.weatherforecastapp.domain.forecastModel.ForecastModel
import com.example.weatherforecastapp.domain.models.WeatherModel

class ForecastAdapter:RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var forecats2: List<ForecastModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root){
        private val context = binding.root.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecats2[position]
        with(holder.binding) {
            val datenDay = forecast.list[0].dtTxt.toString()
            day.text=datenDay
            maxTemp.text = forecast.list[0].main.tempMax.toString()
            minTemp.text = forecast.list[0].main.tempMin.toString()
        }
    }


    override fun getItemCount(): Int = forecats2.size


}