package com.github.ryu.andocchi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.viewmodel.profile.ProfileViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfileTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadFakeRequestUserName() {
        val mockUserRepository = mockk<UserRepository>()
        val target = ProfileViewModel(mockUserRepository)
        val userNameResult = UserInfo().name

        coEvery {
            mockUserRepository.fetchName()
        } returns userNameResult

        val mockObserver = spyk<Observer<String>>()
        target.userName.observeForever(mockObserver)

        target.waitUntilChangedUserName()

        verify {
            mockObserver.onChanged(userNameResult)
        }
    }

    @Test
    fun loadFakeRequestUserLevel() {
        val mockUserRepository = mockk<UserRepository>()
        val target = ProfileViewModel(mockUserRepository)
        val userLevelResult = UserInfo().level

        coEvery {
            mockUserRepository.fetchLevel()
        } returns userLevelResult

        val mockObserver = spyk<Observer<Int>>()
        target.userLevel.observeForever(mockObserver)

        target.waitUntilChangedUserName()

        verify {
            mockObserver.onChanged(userLevelResult)
        }
    }
}

class UserInfo {
    val name: String = "Andocchi"
    val level: Int = 1
}
