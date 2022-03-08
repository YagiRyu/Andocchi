package com.github.ryu.andocchi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.utils.Future
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

//    private val _roadMapList = MutableLiveData<AndroidDeveloperRoadmap>(Future.Proceeding)
//    val roadMap: LiveData<Future<AndroidDeveloperRoadmap>> = _roadMapList

//    private val _roadMapList = MutableLiveData<List<Article>>(emptyList())
//    val roadMap: LiveData<List<Article>> = _roadMapList

    init {
        fetchRoadMap()
    }

    fun fetchRoadMap() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRoadMap()
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    val section = response[0].sections?.get(0)?.nodes?.get(0)?.childNodes?.get(0)
                    Log.d("hello", "fetchRoadMap: ${section}")
                }
            } catch (e: Throwable) {
                Log.d("error", "fetchRoadMap: $e")
            }
        }
    }
}
