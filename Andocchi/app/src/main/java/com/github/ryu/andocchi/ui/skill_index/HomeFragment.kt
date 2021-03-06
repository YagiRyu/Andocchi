package com.github.ryu.andocchi.ui.skill_index

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.adapter.SkillItemAdapter
import com.github.ryu.andocchi.databinding.FragmentHomeBinding
import com.github.ryu.andocchi.viewmodel.skill_index.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val SECTION_LIMIT = 22
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val sectionTitleList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.paths.observe(viewLifecycleOwner, Observer {
            val linearLayoutManager = LinearLayoutManager(view?.context)
            it?.forEach { path ->
                if (path.sections == null || sectionTitleList.size == SECTION_LIMIT)
                    return@forEach
                for (section in path.sections) {
                    sectionTitleList.add(section.title!!)
                }
            }
            val adapter = SkillItemAdapter(sectionTitleList, viewModel.paths.value!!)

            binding.containerRecyclerView.layoutManager = linearLayoutManager
            binding.containerRecyclerView.adapter = adapter
            binding.containerRecyclerView.setHasFixedSize(true)

            adapter.setOnItemClickListener(object : SkillItemAdapter.OnItemClickListener{
                override fun onItemClickListener(pathPosition: Int, skill: String) {
                    // Fragment?????????????????????Path???Position???Section????????????????????????
                    val action = HomeFragmentDirections.actionNavHomeToSectionHomeFragment(pathPosition, skill)
                    findNavController().navigate(action)
                }
            })
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (viewModel.errorMessage.value!!) {
                Toast.makeText(activity, "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
