package com.example.weatherforecastapp.domain.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.databinding.ItemDayBinding
import com.example.weatherforecastapp.domain.forecastModel.ForecastModel
import com.example.weatherforecastapp.domain.models.WeatherModel
import java.text.SimpleDateFormat
import java.util.*
class WeatherAdapter:RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var forecats: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root){
        private val context = binding.root.context

//        fun bind(daily: WeatherModel){
//            with(binding){
//                day.text=unixDay(WeatherModel.)
//            }
//        }
    }

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
//        val forecast = forecats[position]
//        with(holder.binding) {
//            val datenDay=forecast.main.
//
//            maxTemp.text=forecast.list[0].main.tempMax.toString()

        }
    }



    fun extractDateFromDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputFormat.parse(dateTimeString)
        return outputFormat.format(date)
    }


    fun convertDateToDayOfWeek(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }


