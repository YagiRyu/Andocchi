package com.github.ryu.andocchi.viewmodel.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.utils.createMutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _memoList = MutableStateFlow<MutableList<String>>(mutableListOf())
    val memoList: StateFlow<MutableList<String>> = _memoList.asStateFlow()

    init {
        fetchMemoList()
    }

    private fun fetchMemoList() {
        viewModelScope.launch(Dispatchers.Default) {
            _memoList.value = repository.fetchMemo().createMutableList()
        }
    }
}
