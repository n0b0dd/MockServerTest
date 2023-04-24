package com.demo.weather.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.demo.weather.R
import com.demo.weather.databinding.ActivityWeatherBinding
import com.demo.weather.util.CITY_ID
import com.demo.weather.view.base.BaseActivity
import com.demo.weather.viewmodel.CityWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherActivity : BaseActivity<ActivityWeatherBinding>() {
    private val TAG = CityWeatherActivity::class.java.simpleName
//    override val viewModel by viewModels<CityWeatherViewModel> { viewModelFactory }
    private val viewModel : CityWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun getContentView() = R.layout.activity_weather

    override fun setupBinding() {
        binding.viewModel = this.viewModel
        binding.weatherIconUrl = ""
    }

    override fun setupViewModel() {
        viewModel.weatherIconUrlLiveData.observe(this, Observer<String> {
            Log.d(TAG, "Weather Icon updated")
            binding.weatherIconUrl = it
        })
    }

    override fun onResume() {
        super.onResume()
        intent.extras?.getString(CITY_ID)?.let {
            viewModel.checkCurrentWeather(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
