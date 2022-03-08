package com.github.ryu.andocchi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentHomeBinding
import com.github.ryu.andocchi.utils.Future
import com.github.ryu.andocchi.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var layoutManager = LinearLayoutManager(activity)
//        var viewAdapter = SkillViewAdapter()
//
//        this.recyclerView = binding.containerRecyclerView.also {
//            it.layoutManager = layoutManager
//            it.adapter = viewAdapter
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

//        viewModel.fetchRoadMap()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.roadMap.observe(viewLifecycleOwner) {
//            when (it) {
//                is Future.Proceeding -> {
//                    Log.d("hello", "onViewCreated: start")
//                }
//                is Future.Success -> {
//                    Log.d("hello", "onViewCreated: ${viewModel.roadMapList.value}")
//                }
//                is Future.Failure -> {
//                    Log.d("hello", "onViewCreated: failure")
//                }
//            }
//        }
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