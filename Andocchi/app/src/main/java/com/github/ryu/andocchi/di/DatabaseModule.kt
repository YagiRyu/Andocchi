package com.github.ryu.andocchi.di

import android.content.Context
import androidx.room.Room
import com.github.ryu.andocchi.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, UserDatabase::class.java, "database").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(db: UserDatabase) = db.userDao()
}
