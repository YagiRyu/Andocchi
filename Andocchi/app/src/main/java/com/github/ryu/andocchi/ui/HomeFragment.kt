package com.github.ryu.andocchi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.adapter.SkillItemAdapter
import com.github.ryu.andocchi.databinding.FragmentHomeBinding
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    val list = arrayListOf("heelo", "gogo", "imamd", "hello")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.title = "スキル一覧"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.paths.observe(viewLifecycleOwner, Observer {
            val recyclerView = binding.containerRecyclerView
            val linearLayoutManager = LinearLayoutManager(view?.context)
            val adapter = SkillItemAdapter(viewModel.paths.value!!)

            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = adapter

        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.recyclerView?.adapter = null
        this.recyclerView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}