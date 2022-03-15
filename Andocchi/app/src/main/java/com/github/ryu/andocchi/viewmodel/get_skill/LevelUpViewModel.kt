package com.github.ryu.andocchi.viewmodel.get_skill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelUpViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userLevel = MutableLiveData<Int>()
    val userLevel: LiveData<Int> = _userLevel

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    init {
        fetchUserLevel()
        fetchUserName()
    }

    fun fetchUserLevel() {
        viewModelScope.launch(Dispatchers.Main) {
            _userLevel.value = repository.fetchLevel()
            _userName.value = repository.fetchName()
        }
    }

    fun fetchUserName() {
        viewModelScope.launch(Dispatchers.Main) {
            _userName.value = repository.fetchName()
        }
    }
}
