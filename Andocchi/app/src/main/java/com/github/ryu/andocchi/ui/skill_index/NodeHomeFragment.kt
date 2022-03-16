package com.github.ryu.andocchi.ui.skill_index

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.adapter.NodeSkillItemAdapter
import com.github.ryu.andocchi.databinding.FragmentNodeHomeBinding
import com.github.ryu.andocchi.viewmodel.skill_index.NodeHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NodeHomeFragment : Fragment() {

    private val args: NodeHomeFragmentArgs by navArgs()

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentNodeHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NodeHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.displaySection(args.jsonPositionNumber, args.jsonPositionNodeNumber)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNodeHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        viewModel.nodes.observe(viewLifecycleOwner, Observer {
            if (viewModel.nodes.value == null) {
                binding.error.text = "スキルがありません"
            } else {
                recyclerView = binding.containerNodeItemRecyclerView
                val linearLayoutManager = LinearLayoutManager(view?.context)
                val adapter = NodeSkillItemAdapter(viewModel.nodes.value!!)

                recyclerView?.layoutManager = linearLayoutManager
                recyclerView?.adapter = adapter
                recyclerView?.setHasFixedSize(true)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recyclerView?.adapter = null
        recyclerView = null
    }

}