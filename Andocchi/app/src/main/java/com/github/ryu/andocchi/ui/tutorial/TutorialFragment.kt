package com.github.ryu.andocchi.ui.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.viewmodel.tutorial.TutorialScreenViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class TutorialFragment : Fragment() {

    private val viewModel: TutorialScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tutorial, container, false).apply {
            findViewById<ComposeView>(R.id.jetpack_compose).setContent {
                TutorialScreen() {
                    findNavController().navigate(TutorialFragmentDirections.actionTutorialFragmentToNavHome())
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TutorialScreen(navigation: () -> Unit) {

    val pagerState = rememberPagerState(pageCount = 3)

    val FIRST_VIEW = 0
    val SECOND_VIEW = 1
    val THIRD_VIEW = 2

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121640)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Android Developer Roadmap 2022",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Card(
                modifier = Modifier
                    .height(500.dp)
                    .width(350.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    when (it) {
                        FIRST_VIEW -> FirstScreen()
                        SECOND_VIEW -> SecondScreen()
                        THIRD_VIEW -> ThirdScreen(navigation)
                    }
                }
            }
        }
    }
}
