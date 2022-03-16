package com.github.ryu.andocchi.viewmodel.skill_index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.model.Section
import com.github.ryu.andocchi.model.User
import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.utils.createMutableList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {

    private val _paths = MutableLiveData<List<Path>?>(emptyList())
    val paths: LiveData<List<Path>?> = _paths

    private val _section = MutableLiveData<List<Section>>(emptyList())
    val section: LiveData<List<Section>> = _section

    private val _errorMessage = MutableLiveData(false)
    val errorMessage: LiveData<Boolean> = _errorMessage

    companion object {
        private val skillList: MutableList<String> = mutableListOf()
        private val memoList: MutableList<String> = mutableListOf()
        private val USER = User(id = 1, name = "Androcchi", level = 1, memo = memoList, skillList = skillList)
    }

    init {
        fetchRoadMap()
    }

    private fun fetchRoadMap() {
        viewModelScope.launch(Dispatchers.IO) {
//            userRepository.deleteUserInfo(userRepository.fetchUserName()[0])
            if (userRepository.fetchUserName().isEmpty()) {
                userRepository.insertUserInfo(USER)
            }
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    _paths.postValue(it)
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = true
                }
                Log.d("error", "fetchRoadMap: $e")
            }
        }
    }
}
