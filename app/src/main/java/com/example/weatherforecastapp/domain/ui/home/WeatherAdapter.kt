package com.example.weatherforecastapp.domain.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.databinding.ItemDayBinding
import com.example.weatherforecastapp.domain.models.WeatherModel

class WeatherAdapter:RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var forecats: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =forecats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecats[position]
        with(holder.binding) {
            minTemp.text=forecast.main.tempMin.toString()
            maxTemp.text=forecast.main.tempMax.toString()
        }
    }

}