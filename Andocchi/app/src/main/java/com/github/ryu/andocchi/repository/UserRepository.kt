package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.data.UserDao
import com.github.ryu.andocchi.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val dao: UserDao) {
    suspend fun fetchUserName() = dao.fetchUserName()

    fun insertUserInfo(user: User) = dao.insertUserInfo(user)

    fun updateUserInfo(name: String) = dao.updateUserInfo(name)

    suspend fun fetchName() = dao.fetchName()

    fun deleteUserInfo(user: User) = dao.deleteUserInfo(user)
}
