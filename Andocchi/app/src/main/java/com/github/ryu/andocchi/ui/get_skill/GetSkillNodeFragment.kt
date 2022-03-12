package com.github.ryu.andocchi.ui.get_skill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentGetSkillBinding
import com.github.ryu.andocchi.databinding.FragmentGetSkillNodeBinding
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillNodeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetSkillNodeFragment : Fragment() {

    private val args: GetSkillNodeFragmentArgs by navArgs()

    private var recyclerView: RecyclerView? = null

    private var _binding: FragmentGetSkillNodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GetSkillNodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGetSkillNodeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.adapter = null
        recyclerView = null
        _binding = null
    }
}
