package com.github.ryu.andocchi.data

import androidx.room.*
import com.github.ryu.andocchi.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUserInfo(user: User)

    @Query("UPDATE user_table SET name = :name WHERE id = 1")
    fun updateUserName(name: String)

    @Query("UPDATE user_table SET level = :level WHERE id = 1")
    fun updateUserLevel(level: Int)

    @Query("UPDATE user_table SET skill_list = :skillList WHERE id = 1")
    fun updateUserSkill(skillList: MutableList<String>)

    @Query("UPDATE user_table SET memo = :memo WHERE id = 1")
    fun updateMemo(memo: MutableList<String>)

    @Query("SELECT * FROM user_table")
    suspend fun fetchUserName(): List<User>

    @Query("SELECT name FROM user_table")
    suspend fun fetchName(): String

    @Query("SELECT level FROM user_table")
    suspend fun fetchLevel(): Int

    @Query("SELECT skill_list FROM user_table")
    suspend fun fetchSkill(): MutableList<String>

    @Query("SELECT memo FROM user_table")
    suspend fun fetchMemo(): MutableList<String>

    @Delete
    fun deleteUserInfo(user: User)
}
