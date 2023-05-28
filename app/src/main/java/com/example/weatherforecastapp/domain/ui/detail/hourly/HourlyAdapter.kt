package com.example.weatherforecastapp.domain.ui.detail.hourly

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.databinding.ItemDayBinding
import com.example.weatherforecastapp.domain.models.Main

class HourlyAdapter : ListAdapter<Main, HourlyAdapter.HourlyViewHolder>(HourlyDiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyAdapter.HourlyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HourlyAdapter.HourlyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


//    class HourlyViewHolder(private val binding: FragmentHomeBinding){
//        private val
//    }
}

