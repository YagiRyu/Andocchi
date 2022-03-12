package com.github.ryu.andocchi.viewmodel.profile

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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userLevel = MutableLiveData(0)
    val userLevel: LiveData<Int> = _userLevel

    companion object {
        private val skillList: MutableList<String> = mutableListOf()
        private val USER = User(id = 1, name = "Androcchi", level = 1, memo = null, skillList = skillList)
    }

    init {
        setUserNameAndUserLevel()
    }

    private fun setUserNameAndUserLevel() {
        viewModelScope.launch(Dispatchers.Default) {
            if (repository.fetchUserName().isEmpty()) {
                repository.insertUserInfo(USER)
            }

            // LiveDataは、バックグラウンドスレッドで定義できないので、メインスレッドで定義している
            withContext(Dispatchers.Main) {
                _userName.value = repository.fetchUserName()[0].name
                _userLevel.value = repository.fetchUserName()[0].level
            }
            Log.d("Hello", "${repository.fetchUserName()}: ")
        }
    }

    fun waitUntilChangedUserName() {
        viewModelScope.launch(Dispatchers.Main) {
            _userName.value = repository.fetchUserName()[0].name
            _userLevel.value = repository.fetchUserName()[0].level
        }
    }

}
