package com.example.weatherforecastapp.domain.ui.detail.hourly

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherforecastapp.domain.models.Main

class HourlyDiffUtilCallback : DiffUtil.ItemCallback<Main>() {
    override fun areItemsTheSame(oldItem: Main, newItem: Main): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Main, newItem: Main): Boolean {
        return oldItem == newItem
    }
}