package com.github.ryu.andocchi.repository

import com.github.ryu.andocchi.data.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) {
}
