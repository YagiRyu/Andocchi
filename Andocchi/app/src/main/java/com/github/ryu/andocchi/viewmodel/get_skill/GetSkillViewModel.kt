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

    private val _sections = MutableLiveData<Section>()
    val sections: LiveData<Section> = _sections

    private val _nodes = MutableLiveData<Node>()
    val nodes: LiveData<Node> = _nodes

    val state = MutableLiveData<DialogState<SkillDialogFragment>>()

    private val _childNodes = MutableLiveData<ChildNode>()
    val childNode: LiveData<ChildNode> = _childNodes

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _priority = MutableLiveData<Int>()
    val priority: LiveData<Int> = _priority

    private val _isJetpack = MutableLiveData<Boolean>()
    val isJetpack: LiveData<Boolean> = _isJetpack

    private val _checkState = MutableLiveData("未達成")
    val checkState: LiveData<String> = _checkState

    init {
        fetchRoadMap()
    }

    fun fetchRoadMap() {
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

    fun updateUserSkill(skillId: String) {

        viewModelScope.launch(Dispatchers.Default) {
            val userLevel = userRepository.fetchLevel()
            val skillList = userRepository.fetchSkill()[0].split(",").filter { it != "" }.toMutableList()
            skillList.add(skillId)
            userRepository.updateUserSkill(skillList)
            if (skillList.size % 2 == 0) {
                userRepository.updateUserLevel(userLevel + LEVEL_UP)
            }
        }
    }
}
