package com.github.ryu.andocchi.viewmodel.get_skill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _skill = MutableStateFlow<MutableList<String>>(mutableListOf())
    val skill: StateFlow<MutableList<String>> = _skill.asStateFlow()

    init {
        fetchSkill()
    }

    private fun fetchSkill() {
        viewModelScope.launch(Dispatchers.Main) {
            _skill.value = repository.fetchSkill()
        }
    }

    fun updateMemo(memo: String, skillTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val memoList = repository.fetchMemo()
            memoList.add("$skillTitle:$memo")
            repository.updateMemo(memoList)
        }
    }
}
