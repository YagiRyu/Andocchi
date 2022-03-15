package com.github.ryu.andocchi.viewmodel.tutorial

import androidx.lifecycle.ViewModel
import com.github.ryu.andocchi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialScreenViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
}
