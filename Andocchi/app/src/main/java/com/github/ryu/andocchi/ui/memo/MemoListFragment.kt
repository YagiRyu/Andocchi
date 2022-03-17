package com.github.ryu.andocchi.ui.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentMemoListBinding
import com.github.ryu.andocchi.viewmodel.memo.MemoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoListFragment : Fragment() {

    private var _binding: FragmentMemoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MemoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root.apply {
            binding.memoList.setContent {
                MaterialTheme {
                    MemoListScreen()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMemoList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun MemoListScreen() {

    val viewModel: MemoListViewModel = viewModel()
    val memoList = viewModel.memoList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize().padding(top = 30.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121640)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(memoList.value.size) {
                Card(modifier = Modifier
                    .width(350.dp)
                    .fillMaxHeight()
                    .padding(10.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color(R.drawable.is_jetpack_gradient_color)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = memoList.value[it].split(":")[0],
                            color = Color.Green,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Divider()
                        Text(text = memoList.value[it].split(":")[1],
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
