package com.example.weatherforecastapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherforecastapp.databinding.ActivityMainBinding
import com.example.weatherforecastapp.domain.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}