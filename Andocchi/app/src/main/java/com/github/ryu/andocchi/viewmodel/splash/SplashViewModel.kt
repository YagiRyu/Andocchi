package com.github.ryu.andocchi.viewmodel.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.User
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _isExistUser = MutableLiveData<Boolean>(false)
    val isExistUser: LiveData<Boolean> = _isExistUser

    companion object {
        private val skillList: MutableList<String> = mutableListOf()
        private val memoList: MutableList<String> = mutableListOf()
        private val USER = User(id = 1, name = "Androcchi", level = 1, memo = memoList, skillList = skillList)
    }

    init {
        fetchUserData()
    }

    fun fetchUserData() {
//        viewModelScope.launch(Dispatchers.Default) {
//            repository.insertUserInfo(USER)
//        }
        viewModelScope.launch(Dispatchers.Main) {
            if (repository.fetchUserName().isEmpty()) {
                _isExistUser.value = true
            }
            Log.d("Hello", "fetchUserData: ${isExistUser.value}")
            Log.d("Hello", "fetchUserData: ${repository.fetchUserName()}")
        }
    }
}
