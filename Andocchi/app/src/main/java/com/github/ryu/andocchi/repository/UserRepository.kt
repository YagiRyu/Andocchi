package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.data.UserDao
import com.github.ryu.andocchi.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val dao: UserDao) {
    suspend fun fetchUserName() = dao.fetchUserName()

    fun insertUserInfo(user: User) = dao.insertUserInfo(user)

    fun updateUserName(name: String) = dao.updateUserName(name)

    fun updateUserLevel(level: Int) = dao.updateUserLevel(level)

    fun updateUserSkill(skillList: MutableList<String>) = dao.updateUserSkill(skillList)

    fun updateMemo(memo: MutableList<String>) = dao.updateMemo(memo)

    suspend fun fetchName() = dao.fetchName()

    suspend fun fetchLevel() = dao.fetchLevel()

    suspend fun fetchSkill() = dao.fetchSkill()

    suspend fun fetchMemo() = dao.fetchMemo()

    fun deleteUserInfo(user: User) = dao.deleteUserInfo(user)
}
