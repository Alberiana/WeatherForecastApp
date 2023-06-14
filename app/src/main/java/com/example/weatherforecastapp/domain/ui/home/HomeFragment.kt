package com.example.weatherforecastapp.domain.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.domain.models.WeatherModel


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val adapter = WeatherAdapter()
    private val forecastAdapter = ForecastAdapter()
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()
    private var isLayoutAdded = false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()


        viewModel.getWeatherList("Prishtina", "21c7ee580ddbe56e1dac2d7807624227")
        viewModel.getForecastData("Prishtina", "21c7ee580ddbe56e1dac2d7807624227")

        with(binding) {
            layoutManager = LinearLayoutManager(activity)
            dailyForecastList.adapter = adapter
            dailyForecastList.layoutManager = layoutManager
            dailyForecastList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })


            var isSelected = false
            fav.setOnClickListener {
                isSelected = !isSelected
                when (isSelected) {
                    true -> fav.setImageResource(R.drawable.baseline_star_24)
                    false -> fav.setImageResource(R.drawable.baseline_star_border_24)
                }
            }

            searchBar.setOnEditorActionListener { _, actionId, _ ->
                searchBar.visibility = View.VISIBLE
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var query = binding.searchBar.text.toString().trim()
                    viewModel.getWeatherList(query, "21c7ee580ddbe56e1dac2d7807624227")
                    viewModel.getForecastData(query, "21c7ee580ddbe56e1dac2d7807624227")
                    val inputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
                    return@setOnEditorActionListener true
                }
                false
            }

        }
    }

    private fun observeViewModel() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weatherList ->
            adapter.forecats = weatherList
            binding.loadDaily.visibility = View.GONE
            if (weatherList.isNotEmpty()) {
                val weatherData = weatherList[0]
                val temperature = weatherData.main.temp
                val cityName = weatherData.name
                val pressure = weatherData.main.pressure
                val humidity = weatherData.main.humidity
                val wind = weatherData.wind.speed
                val sunR = weatherData.sys.sunrise
                val temC= temperature-273.15
                val description=weatherData.weather[0].description


                binding.temperature.text = "${temC.toInt()}°C"
                binding.main.text=description.toString()
                binding.location.text = cityName
                binding.detailSun.text = pressure.toString()
                binding.detailH.text = "${humidity.toInt()}%"
                binding.detailWind.text = "${wind.toInt()}km/h"
                binding.detailSunRS.text = sunR.toString()
            }
        }


                viewModel.forecastLiveData.observe(viewLifecycleOwner) { forecastList ->
            binding.loadDaily.visibility = View.GONE
            if (forecastList.isNotEmpty() && !isLayoutAdded) {
                Log.d("ForecastList", "Size: ${forecastList.size}")

                val layoutManager = LinearLayoutManager(requireContext())
                binding.dailyForecastList.layoutManager = layoutManager
                binding.dailyForecastList.adapter = forecastAdapter
                forecastAdapter.forecats2 = forecastList
                forecastAdapter.notifyDataSetChanged()

                isLayoutAdded = true
                for (i in 0 until forecastList.size) {
                    val forecastData = forecastList[i]
                    val day = forecastData.list[0].dtTxt.toString()

                    forecastAdapter.forecats2[i].list[0].dtTxt = day
                }
            }
        }
    }



}
