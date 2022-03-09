package com.github.ryu.andocchi.viewmodel.skill_index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.ChildNode
import com.github.ryu.andocchi.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodeHomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _nodes = MutableLiveData<List<ChildNode>?>(emptyList())
    val nodes: LiveData<List<ChildNode>?> = _nodes

    private val _errorMessage = MutableLiveData(false)
    val errorMessage: LiveData<Boolean> = _errorMessage

    fun displaySection(position: Int, nodePosition: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var i = 0
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    while (i <= response[position].sections?.get(0)?.nodes?.size!!) {
                        _nodes.postValue(response[position].sections?.get(0)?.nodes?.get(nodePosition)?.childNodes)
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
