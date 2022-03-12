package com.github.ryu.andocchi.viewmodel.skill_index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.model.Section
import com.github.ryu.andocchi.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _paths = MutableLiveData<List<Path>?>(emptyList())
    val paths: LiveData<List<Path>?> = _paths

    private val _errorMessage = MutableLiveData(false)
    val errorMessage: LiveData<Boolean> = _errorMessage

    init {
        fetchRoadMap()
    }

    fun fetchRoadMap() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    _paths.postValue(response)
                    delay(1000L)
                }
            } catch (e: Throwable) {
                _errorMessage.value = true
                Log.d("error", "fetchRoadMap: $e")
            }
        }
    }
}
