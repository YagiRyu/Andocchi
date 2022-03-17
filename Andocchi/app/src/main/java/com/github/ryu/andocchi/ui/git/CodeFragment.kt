package com.github.ryu.andocchi.ui.git

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentCodeBinding
import com.github.ryu.andocchi.model.Github
import com.github.ryu.andocchi.viewmodel.git.GitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CodeFragment : Fragment() {

    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root.apply {
            binding.codeJetpackCompose.setContent {
                MaterialTheme {
                    CodeScreen()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Preview
@Composable
fun CodeScreen() {

    val viewModel: GitViewModel = viewModel()
    val skillRanking = viewModel.starRanking.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121640))
            .padding(horizontal = 3.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 30.dp)) {
                items(skillRanking.value.items.size) {
                    Spacer(modifier = Modifier.height(10.dp))
                    if (it != 0) {
                        SkillRankingCard(skillRanking, it)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
private fun SkillRankingCard(
    skillRanking: State<Github>,
    it: Int
) {

    val enabled = remember {
        mutableStateOf(false)
    }

    if (enabled.value) {
        Column() {
            WebView(skillRanking = skillRanking, it = it)
        }
    }

    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable { enabled.value = true },
        backgroundColor = Color(R.drawable.is_jetpack_gradient_color),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = skillRanking.value.items[it].full_name,
                color = Color.White,
                fontSize = 25.sp
            )
            Text(
                text = skillRanking.value.items[it].stargazers_count.toString(),
                color = Color.Yellow,
                fontSize = 20.sp
            )

            Text(
                text = skillRanking.value.items[it].description,
                color = Color.White,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            AsyncImage(
                model = skillRanking.value.items[it].owner.avatar_url,
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun WebView(
    skillRanking: State<Github>,
    it: Int
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = ::WebView,
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://github.com/${skillRanking.value.items[it].full_name}")
        }
    )
}
