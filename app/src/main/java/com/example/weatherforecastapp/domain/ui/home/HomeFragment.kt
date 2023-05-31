package com.example.weatherforecastapp.domain.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private val args:HomeFragmentArgs by navArgs()

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

//            val weatherList = arguments?.getParcelableArrayList<Main>("dataType")
//            if (weatherList != null) {
//                adapter.forecats=weatherList
//            }

            // Set up your RecyclerView and adapter here

        when(args.dataType){

            else->viewModel.getWeatherList(viewModel.currentPage.toString(),"21c7ee580ddbe56e1dac2d7807624227")
        }


        with(binding) {
            layoutManager = LinearLayoutManager(activity)
            dailyForecastList.adapter=adapter
            dailyForecastList.layoutManager=layoutManager
            dailyForecastList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })

            searchBar.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val query = binding.searchBar.text.toString().trim()
                    viewModel.getWeatherList(query, "21c7ee580ddbe56e1dac2d7807624227")

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
            binding.loadDaily.visibility=View.GONE
            if (weatherList.isNotEmpty()) {
                val weatherData = weatherList[0]
                val temperature = weatherData.main.temp
                val cityName = weatherData.name
                val uvIndex=weatherData.main.pressure
                val humidity=weatherData.main.humidity
                val wind=weatherData.wind.speed
                val sunR=weatherData.sys.sunrise
//                val description=weatherData.weather
                binding.temperature.text = temperature.toString()
                binding.location.text = cityName
                binding.detailSun.text=uvIndex.toString()
                binding.detailH.text=humidity.toString()
                binding.detailWind.text=wind.toString()
                binding.detailSunRS.text=sunR.toString()
            }
        }
    }
}