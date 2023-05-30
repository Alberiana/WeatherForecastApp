package com.example.weatherforecastapp.domain.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecastapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    var currentPage = 1

    lateinit var binding: FragmentHomeBinding
    private val adapter = WeatherAdapter()
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
//    private val args: HomeFragmentArgs by navArgs()
//    private val args: HomeFragmentArgs by navArgs()

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

        var searchedCity=binding.searchBar

//        when(args.dataType){
//            else->viewModel.getWeatherList(searchedCity.toString(), "21c7ee580ddbe56e1dac2d7807624227")
//
//        }

        with(binding) {
            layoutManager = LinearLayoutManager(requireActivity())
//            forecastsList.layoutManager = layoutManager

//            forecastsList.addOnScrollListener(object : OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                }
//            })


            searchBar.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val query = binding.searchBar.text.toString().trim()
                    viewModel.searchCity(query)
                    // Hide the keyboard
                    val inputMethodManager =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(binding.searchBar.windowToken, 0)
                    return@setOnEditorActionListener true
                }
                false
            }


        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) { weatherList ->
            if(weatherList.isNotEmpty()){
                val weather=weatherList[0]
                val temperature=weather.main?.temp
                val cityName=weather.name

                viewModel.getWeatherList(cityName,"21c7ee580ddbe56e1dac2d7807624227\n")
                binding.temperature.text=temperature?.toString()?:""
                binding.location.text=cityName?:""

                adapter.forecats=weatherList

            }
        }
    }

}