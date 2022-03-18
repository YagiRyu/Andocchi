package com.github.ryu.andocchi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.model.User
import com.github.ryu.andocchi.network.GithubService
import com.github.ryu.andocchi.repository.GithubRepository
import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.viewmodel.skill_index.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class HomeUnitTest {

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
    fun loadFakeRequestPathIsValid() {
        val mockHomeRepository = mockk<HomeRepository>()
        val mockUserRepository = mockk<UserRepository>()
        val target = HomeViewModel(mockHomeRepository, mockUserRepository)
        val pathResult = DemoRequest().path

        coEvery {
            mockHomeRepository.fetchRoadMap()
        } returns pathResult

        val mockObserver = spyk<Observer<List<Path>?>>()
        target.paths.observeForever(mockObserver)

        target.fetchRoadMap()

        verify {
            mockObserver.onChanged(pathResult)
        }
    }
}

class DemoRequest {
    val path: List<Path>? = null
}
