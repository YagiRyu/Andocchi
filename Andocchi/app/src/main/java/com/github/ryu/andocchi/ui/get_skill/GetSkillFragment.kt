package com.github.ryu.andocchi.ui.get_skill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.stickyheader.StickyHeaderLinearLayoutManager
import com.github.ryu.andocchi.databinding.FragmentGetSkillBinding
import com.github.ryu.andocchi.adapter.StickyHeaderController
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GetSkillFragment : Fragment() {

    private var _binding: FragmentGetSkillBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GetSkillViewModel by viewModels()

    companion object {
        val TAG = GetSkillFragment::class.java.simpleName ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetSkillBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.paths.observe(viewLifecycleOwner, Observer {
            CoroutineScope(Dispatchers.Main).launch {
                val skillList = viewModel.fetchSkillList()

                val stickyHeaderController = StickyHeaderController(skillList) { position: Int->
                    SkillDialogFragment.show("このスキルを習得しますか？",
                        position.toString(),
                        skillList,
                        childFragmentManager,
                        TAG) {}
                }
                binding.skillContainerRecyclerView.adapter = stickyHeaderController.adapter
                binding.skillContainerRecyclerView.layoutManager = StickyHeaderLinearLayoutManager(requireContext())

                stickyHeaderController.setData(viewModel.paths.value!!, viewModel.paths.value!!)
                binding.skillContainerRecyclerView.setController(stickyHeaderController)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DialogState.OK -> requireActivity().finish()
                is DialogState.Cancel -> {
                    Log.d("failure", "onViewCreated: failure")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Hello", "onStart: Hello")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
