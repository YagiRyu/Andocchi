package com.github.ryu.andocchi.data

import androidx.room.*
import com.github.ryu.andocchi.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUserInfo(user: User)

    @Query("UPDATE user_table SET name = :name WHERE id = 1")
    fun updateUserInfo(name: String)

    @Query("UPDATE user_table SET level = :level WHERE id = 1")
    fun updateUserLevel(level: Int)

    @Query("SELECT * FROM user_table")
    suspend fun fetchUserName(): List<User>

    @Query("SELECT name FROM user_table")
    suspend fun fetchName(): String

    @Delete
    fun deleteUserInfo(user: User)

//    @Query("SELECT * FROM user_table SET name=:name WHERE name=:old")
//    fun updateUserName(name: String, old: String)
}
