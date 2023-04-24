package com.demo.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weather.data.AppRepository
import com.demo.weather.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeScreenViewModel @Inject constructor(
    private val repo: AppRepository
): BaseViewModel() {
    private val TAG = HomeScreenViewModel::class.java.simpleName
    private val _cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = emptyList() }
    val cities: LiveData<List<City>> = _cities

    init {
        Log.d(TAG, "Injection discovered")
    }

    fun queryCityList(query: String) {
        runOnIO {
            repo.queryCities(query)
                .flowOn(Dispatchers.IO)
                .collect { r ->
                    onOperationSucceed(r)
                }
        }
    }

    private suspend fun onOperationSucceed(obj: List<City>) {
        backOnMain {
            _cities.value = obj
        }
    }
}
