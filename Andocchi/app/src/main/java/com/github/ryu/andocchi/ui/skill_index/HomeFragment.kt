package com.github.ryu.andocchi.ui.skill_index

import android.os.Bundle
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
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.adapter.SkillItemAdapter
import com.github.ryu.andocchi.databinding.FragmentHomeBinding
import com.github.ryu.andocchi.viewmodel.skill_index.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.paths.observe(viewLifecycleOwner, Observer {
            recyclerView = binding.containerRecyclerView
            val linearLayoutManager = LinearLayoutManager(view?.context)
            val adapter = SkillItemAdapter(viewModel.paths.value!!)

            recyclerView?.layoutManager = linearLayoutManager
            recyclerView?.adapter = adapter
            recyclerView?.setHasFixedSize(true)

            adapter.setOnItemClickListener(object : SkillItemAdapter.OnItemClickListener{
                override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                    // Fragment間で、押されたPathのPositionをSectionスクリーンに渡す
                    val action = HomeFragmentDirections.actionNavHomeToSectionHomeFragment(position)
                    findNavController().navigate(action)
                }
            })
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (viewModel.errorMessage.value!!) {
                Toast.makeText(activity, "データを取得できませんでした。", Toast.LENGTH_SHORT).show()
            }
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
