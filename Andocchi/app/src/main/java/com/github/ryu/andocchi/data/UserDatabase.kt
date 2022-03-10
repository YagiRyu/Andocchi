package com.github.ryu.andocchi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ryu.andocchi.model.User
import com.github.ryu.andocchi.utils.RoomTypeConverters

@Database(entities = [User::class], version = 6, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
