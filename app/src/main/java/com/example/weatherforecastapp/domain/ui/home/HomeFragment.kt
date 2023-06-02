package com.example.weatherforecastapp.domain.ui.home

import android.content.Context
import android.os.Bundle
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
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.domain.models.WeatherModel


class HomeFragment : Fragment() {
    var currentPage = 1
    lateinit var binding: FragmentHomeBinding
    private val adapter = WeatherAdapter()
    private val forecastAdapter = ForecastAdapter()
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()

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
        observeSeconViewModel()

//            val weatherList = arguments?.getParcelableArrayList<Main>("dataType")
//            if (weatherList != null) {
//                adapter.forecats=weatherList
//            }

        // Set up your RecyclerView and adapter here

        when (args.dataType) {

            else -> viewModel.getForecastData(
                viewModel.currentPage.toString(),
                "21c7ee580ddbe56e1dac2d7807624227"
            )
        }


        with(binding) {
            layoutManager = LinearLayoutManager(activity)
            dailyForecastList.adapter = adapter
            dailyForecastList.layoutManager = layoutManager
            dailyForecastList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })


            searchBar.setOnEditorActionListener { _, actionId, _ ->
                searchBar.isFocusable = false // Disable focusability of the search bar
                searchBar.isFocusableInTouchMode = false // Disable touch mode focusability of the search bar
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



//            searchBar.doOnTextChanged{text, start, before,count->
//                viewModel.searchCity(text.toString())
//
//            }

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
    }

    private fun observeSeconViewModel() {
        viewModel.forecastLiveData.observe(viewLifecycleOwner) { forecastList ->
            forecastAdapter.forecats2 = forecastList
            binding.loadDaily.visibility = View.GONE
            if (forecastList.isEmpty()) {
                val forecastData = forecastList[0]
                val day = forecastData.list[0].dt.toString()
            }
        }
    }
}