package com.github.ryu.andocchi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.ryu.andocchi.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUserInfo(user: User)

    @Update
    fun updateUserInfo(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun fetchUserName(): User

//    @Query("SELECT * FROM user_table SET name=:name WHERE name=:old")
//    fun updateUserName(name: String, old: String)
}
