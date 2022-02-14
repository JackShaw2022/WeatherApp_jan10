package com.example.weatherapp_jan10.features.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp_jan10.databinding.FragmentCitySearchBinding
import com.example.weatherapp_jan10.features.weather.viewmodel.WeatherViewModel
import com.example.weatherapp_jan10.utils.Resource
import com.example.weatherapp_jan10.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitySearchFragment : Fragment() {

    private var _binding: FragmentCitySearchBinding? = null
    private val binding: FragmentCitySearchBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            searchBtn.setOnClickListener {
                val input = cityNameEt.editText?.text.toString()
                cityNameEt.isErrorEnabled = input.isBlank()
                cityNameEt.error = if (input.isBlank()) "Invalid Input" else ""

                if (input.isNotBlank())
                    viewModel.getWeatherForCity(input)
            }

            viewModel.weather.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateUi(true)
                    }
                    is Resource.Success  -> {
                        updateUi(false)
                        showToast(resource.data.toString())
                    }
                    is Resource.Error -> {
                        updateUi(false)
                        cityNameEt.isErrorEnabled = true
                        cityNameEt.error = resource.msg
                    }
                }
            }
        }
    }

    private fun updateUi(isLoading: Boolean) {
        with(binding) {
            progressBar.isVisible = isLoading
            cityNameEt.isEnabled = !isLoading
            searchBtn.isEnabled = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}