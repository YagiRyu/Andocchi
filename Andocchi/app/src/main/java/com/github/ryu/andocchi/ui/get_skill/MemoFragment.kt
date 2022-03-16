package com.github.ryu.andocchi.ui.get_skill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentMemoBinding
import com.github.ryu.andocchi.viewmodel.get_skill.MemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoFragment : Fragment() {

    private val args: MemoFragmentArgs by navArgs()

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        Log.d("Hello", "onCreateView: ${args.skillTitle}")
        return binding.root.apply {
            binding.memoJetpackCompose.setContent {
                MaterialTheme {
                    MemoEditScreen(args.skillTitle) {
                        findNavController().navigate(MemoFragmentDirections.actionMemoFragmentToNavSkill())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun MemoEditScreen(skillTitle: String, navigation: () -> Unit) {

    val viewModel: MemoViewModel = viewModel()
    val memoValue = rememberSaveable { mutableStateOf("") }
    val enabled = remember { mutableStateOf(false) }

    val skill = viewModel.skill.collectAsState()

    LaunchedEffect(key1 = memoValue.value) {
        enabled.value = memoValue.value.isNotEmpty()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121640)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = memoValue.value,
                onValueChange = { newValue: String ->
                    memoValue.value = newValue
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(12.dp)
            )
            
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    viewModel.updateMemo(memoValue.value, skillTitle)
                    navigation()
                          },
                enabled = enabled.value,
                colors = ButtonDefaults.buttonColors(Color(R.drawable.is_jetpack_gradient_color))
            ) {
                Text(text = "メモる", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
