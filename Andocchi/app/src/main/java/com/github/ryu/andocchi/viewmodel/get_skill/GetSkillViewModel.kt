package com.github.ryu.andocchi.viewmodel.get_skill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryu.andocchi.model.ChildNode
import com.github.ryu.andocchi.model.Node
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.model.Section
import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.ui.get_skill.DialogState
import com.github.ryu.andocchi.ui.get_skill.SkillDialogFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetSkillViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {

    companion object {
        const val LEVEL_UP = 1
    }

    private val _paths = MutableLiveData<List<Path>?>()
    val paths: LiveData<List<Path>?> = _paths

    val state = MutableLiveData<DialogState<SkillDialogFragment>>()

    init {
        fetchRoadMap()
    }

    private fun fetchRoadMap() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchRoadMap()
                response?.let {
                    _paths.postValue(response)
                    delay(1000L)
                }
            } catch (e: Throwable) {
//                _errorMessage.value = true
                Log.d("error", "fetchRoadMap: $e")
            }
        }
    }

    fun isContainSkill(skillId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val skillList = userRepository.fetchSkill().createMutableList()
            if (skillId in skillList) {
                updateUserSkillDown(skillId)
            } else {
                updateUserSkill(skillId)
            }
        }
    }

    private suspend fun updateUserSkill(skillId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val userLevel = userRepository.fetchLevel()
            val skillList = userRepository.fetchSkill().createMutableList()
            skillList.add(skillId)
            userRepository.updateUserSkill(skillList)
            if (skillList.size % 2 == 0) {
                userRepository.updateUserLevel(userLevel + LEVEL_UP)
            }
        }
    }

    private suspend fun updateUserSkillDown(skillId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val userLevel = userRepository.fetchLevel()
            val skillList = userRepository.fetchSkill().createMutableList()
            skillList.remove(skillId)
            userRepository.updateUserSkill(skillList)
            if (skillList.size % 2 != 0) {
                userRepository.updateUserLevel(userLevel - LEVEL_UP)
            }
        }
    }

    private fun MutableList<String>.createMutableList() =
        this[0].split(",").filter { it != "" }.toMutableList()
}
