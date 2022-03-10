package com.github.ryu.andocchi.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userName = MutableLiveData<String>("Hello")
    val userName: LiveData<String> = _userName

    private val _userLevel = MutableLiveData(0)
    val userLevel: LiveData<Int> = _userLevel

}
