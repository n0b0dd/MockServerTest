package com.demo.weather.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weather.BuildConfig
import com.demo.weather.R
import com.demo.weather.databinding.ActivityHomeBinding
import com.demo.weather.model.City
import com.demo.weather.util.CITY_ID
import com.demo.weather.view.base.BaseActivity
import com.demo.weather.view.widget.DividerItemDecorator
import com.demo.weather.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeScreenActivity : BaseActivity<ActivityHomeBinding>() {
    private val TAG = HomeScreenActivity::class.java.simpleName
    private lateinit var adapter: HomeScreenAdapter
    val viewModel :HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.viewModel = viewModel
//        initUI()
        Log.d(TAG, "onCreate: $viewModel")
    }

    override fun onResume() {
        super.onResume()
//        viewModel.queryCityList(CITY_NAME_PREFIX)
    }

    override fun getContentView() = R.layout.activity_home

    override fun setupBinding() { }

    override fun setupViewModel() {
        viewModel.cities.observe(this, Observer<List<City>> {
            Log.d(TAG, "List updated")
            adapter.updateData(it)
        })
    }

    private fun initUI() {
        adapter = HomeScreenAdapter(viewModel.cities.value ?: emptyList())
        adapter.delegate = object : CityViewHolder.Delegate {
            override fun onItemClick(city: String, view: View) {
                val intent = Intent(context, CityWeatherActivity::class.java)
                intent.putExtra(CITY_ID, city)
                startActivity(intent)
            }
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.divider_default)!!)
        recycler_view.addItemDecoration(itemDecorator)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        // Inflate the menu; this adds items to the action bar if it is present.
        Toast.makeText(this, "Flavor ${BuildConfig.FLAVOR} ${context.packageName}", Toast.LENGTH_SHORT).show()
        if (BuildConfig.FLAVOR == "mock") {
            menuInflater.inflate(R.menu.home_menu, menu)

            val settingMenu = menu.findItem(R.id.action_setting)
            settingMenu?.setOnMenuItemClickListener {
                val intent = Intent()
                // The activity is from debug build, need to access by class name
                intent.setClassName(context, "${context.packageName}.view.SettingsActivity")
                startActivity(intent)
                true
            }
        }
        return true
    }

}
