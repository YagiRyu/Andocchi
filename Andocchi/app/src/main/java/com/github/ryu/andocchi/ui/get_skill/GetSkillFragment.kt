package com.github.ryu.andocchi.ui.get_skill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.airbnb.epoxy.stickyheader.StickyHeaderLinearLayoutManager
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.adapter.GetSkillAdapter
import com.github.ryu.andocchi.adapter.SkillItemAdapter
import com.github.ryu.andocchi.data.UserDatabase
import com.github.ryu.andocchi.databinding.FragmentGetSkillBinding
import com.github.ryu.andocchi.model.Item
import com.github.ryu.andocchi.ui.skill_index.HomeFragmentDirections
import com.github.ryu.andocchi.utils.StickyHeaderController
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillViewModel
import com.github.ryu.andocchi.viewmodel.skill_index.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class GetSkillFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentGetSkillBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GetSkillViewModel by viewModels()

    var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetSkillBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.paths.observe(viewLifecycleOwner, Observer {
            val stickyHeaderController = StickyHeaderController()
            binding.skillContainerRecyclerView.adapter = stickyHeaderController.adapter
            binding.skillContainerRecyclerView.layoutManager = StickyHeaderLinearLayoutManager(requireContext())

            stickyHeaderController.setData(viewModel.paths.value!!, viewModel.paths.value!!)
            binding.skillContainerRecyclerView.setController(stickyHeaderController)
            stickyHeaderController.click(object : StickyHeaderController.OnClickListener{
                override fun onSelected(id: Int) {
                    Log.d("gogog", "onSelected: halloe")
                }
            })
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recyclerView?.adapter = null
        recyclerView = null
    }

}
