package com.example.weatherforecastapp.domain.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecastapp.databinding.FragmentHomeBinding
import com.example.weatherforecastapp.domain.models.WeatherForescast
import com.example.weatherforecastapp.domain.ui.detail.ForecastAdapter

class HomeFragment: Fragment(){
    lateinit var binding: FragmentHomeBinding
    private val adapter= ForecastAdapter()
    lateinit var layoutManager: LinearLayoutManager
    private val viewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()



    //    private val viewModel: HomeViewModel by viewModels()
//    private val args: HomeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        when(arguments)
        with(binding) {
            layoutManager = LinearLayoutManager(activity)
            forecastsList.adapter = adapter
            forecastsList.layoutManager = layoutManager
            forecastsList.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
//            searchBar.doOnTextChanged { text, start, before, count ->
//                viewModel.searchCharacters(text.toString())
//            }
        }
    }

    private fun observeViewModel(){
        viewModel.forecastLiveData.observe(viewLifecycleOwner){
            adapter.forecasts=it
            binding.loadMoreloader.visibility=  View.GONE
        }
    }

}