package com.github.ryu.andocchi.ui.skill_index

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.adapter.SectionSkillItemAdapter
import com.github.ryu.andocchi.adapter.SkillItemAdapter
import com.github.ryu.andocchi.databinding.FragmentSectionHomeBinding
import com.github.ryu.andocchi.viewmodel.skill_index.SectionHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionHomeFragment : Fragment() {

    // HomeFragmentからの引数
    private val args: SectionHomeFragmentArgs by navArgs()

    private var _binding: FragmentSectionHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SectionHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Hello", "onCreate: ${args.jsonPositionNumber}")
        viewModel.displaySection(args.jsonPositionNumber, args.pathPositionNumber)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        viewModel.sections.observe(viewLifecycleOwner, Observer {
            val linearLayoutManager = LinearLayoutManager(view?.context)
            val adapter = SectionSkillItemAdapter(viewModel.sections.value!!)

            binding.containerSectionItemRecyclerView.layoutManager = linearLayoutManager
            binding.containerSectionItemRecyclerView.adapter = adapter
            binding.containerSectionItemRecyclerView.setHasFixedSize(true)

            adapter.setOnItemClickListener(object : SectionSkillItemAdapter.OnItemClickListener{
                override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                    // Fragment間で、押されたPathのPositionをSectionスクリーンに渡す
                    val action = SectionHomeFragmentDirections.actionSectionHomeFragmentToNodeHomeFragment(args.jsonPositionNumber, position)
                    findNavController().navigate(action)
                }
            })
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}