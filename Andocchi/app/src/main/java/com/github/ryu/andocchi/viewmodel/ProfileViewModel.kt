package com.github.ryu.andocchi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>("Hello")
    val userName: LiveData<String> = _userName

    private val _userLevel = MutableLiveData(0)
    val userLevel: LiveData<Int> = _userLevel

}
