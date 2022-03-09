package com.github.ryu.andocchi.viewmodel.skill_index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.Node
import com.github.ryu.andocchi.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionHomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _sections = MutableLiveData<List<Node>?>(emptyList())
    val sections: LiveData<List<Node>?> = _sections

    private val _errorMessage = MutableLiveData(false)
    val errorMessage: LiveData<Boolean> = _errorMessage

    fun displaySection(position: Int) {
        var i = 0
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    while (i < response[position].sections?.size!!) {
                        _sections.postValue(response[position].sections?.get(i)?.nodes)
                        i++
                    }
                }
            } catch (e: IllegalArgumentException) {
                _errorMessage.value = true
                Log.d("hello", "displaySection: $e")
            }
        }
    }
}
