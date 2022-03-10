package com.github.ryu.andocchi.viewmodel.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ryu.andocchi.model.User
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _editText = MutableLiveData<String>()
    val editText: LiveData<String> = _editText

    private val _enabled = MutableLiveData<Boolean>(false)
    val enabled: LiveData<Boolean> = _enabled

    val textCount: MediatorLiveData<Int> = MediatorLiveData()

    private lateinit var user: User

    init {
        textCount.value = 0
        textCount.addSource(_editText) {
            val count = _editText.value?.length
            textCount.postValue(count)
        }
    }

    fun validateUserName(editText: String) {
        _enabled.value = editText.isNotEmpty()
    }

    fun updateUserName(editText: String) {
        this._editText.postValue(editText)
    }

    fun updateUserInfo() {
        Log.d("Hello", "updateUserInfo: ${editText.value}")
    }
}
