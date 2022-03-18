package com.github.ryu.andocchi.viewmodel.git

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.Github
import com.github.ryu.andocchi.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _error = MutableStateFlow<Boolean>(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    private val _starRanking: MutableStateFlow<Github> = MutableStateFlow(Github(emptyList()))
    val starRanking: StateFlow<Github> = _starRanking.asStateFlow()

    init {
        fetchStarRanking("Kotlin")
    }

    private fun fetchStarRanking(language: String) =
        viewModelScope.launch {
            repository.getStarRanking(language, "stars", "10").collect {
                _starRanking.value = it.body()!!
            }
        }
}
