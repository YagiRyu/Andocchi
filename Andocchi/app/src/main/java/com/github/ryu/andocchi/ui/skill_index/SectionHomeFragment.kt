package com.github.ryu.andocchi.ui.skill_index

import android.os.Bundle
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

    private var recyclerView: RecyclerView? = null

    // HomeFragmentからの引数
    private val args: SectionHomeFragmentArgs by navArgs()

    private var _binding: FragmentSectionHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SectionHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.displaySection(args.jsonPositionNumber)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.sections.observe(viewLifecycleOwner, Observer {
            recyclerView = binding.containerSectionItemRecyclerView
            val linearLayoutManager = LinearLayoutManager(view?.context)
            val adapter = SectionSkillItemAdapter(viewModel.sections.value!!)

            recyclerView?.layoutManager = linearLayoutManager
            recyclerView?.adapter = adapter
            recyclerView?.setHasFixedSize(true)

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
        this.recyclerView?.adapter = null
        this.recyclerView = null
    }
}