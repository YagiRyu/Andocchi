package com.github.ryu.andocchi.viewmodel.get_skill

import androidx.lifecycle.ViewModel
import com.github.ryu.andocchi.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetSkillNodeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
}
