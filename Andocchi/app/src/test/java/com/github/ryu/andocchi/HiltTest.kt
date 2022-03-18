package com.github.ryu.andocchi

import com.github.ryu.andocchi.repository.HomeRepository
import com.github.ryu.andocchi.repository.UserRepository
import com.github.ryu.andocchi.viewmodel.get_skill.MemoViewModel
import com.github.ryu.andocchi.viewmodel.profile.ProfileViewModel
import com.github.ryu.andocchi.viewmodel.skill_index.HomeViewModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(MockitoJUnitRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class HiltTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject lateinit var repository: HomeRepository
    @Inject lateinit var userRepository: UserRepository

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testHomeViewModelInject() {
        val viewModel = HomeViewModel(repository, userRepository)
        Assert.assertEquals(viewModel, HomeViewModel(repository, userRepository))
    }

    @Test
    fun testProfileViewModelInject() {
        val viewModel = ProfileViewModel(userRepository)
        Assert.assertEquals(viewModel, ProfileViewModel(userRepository))
    }

    @Test
    fun testMemoViewModelInject() {
        val viewModel = MemoViewModel(userRepository)
        Assert.assertEquals(viewModel, MemoViewModel(userRepository))
    }
}
