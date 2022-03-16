package com.github.ryu.andocchi.ui.get_skill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentLevelUpBinding
import com.github.ryu.andocchi.ui.profile.ProfileFragment
import com.github.ryu.andocchi.viewmodel.get_skill.LevelUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LevelUpFragment : Fragment() {

    private val args: LevelUpFragmentArgs by navArgs()

    private var _binding: FragmentLevelUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LevelUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLevelUpBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        viewModel.userLevel.observe(viewLifecycleOwner, Observer {
            changeImageAndBackgroundByLevel(it)
        })

        binding.levelUpMemoButton.setOnClickListener {
            findNavController().navigate(LevelUpFragmentDirections.actionLevelUpFragmentToMemoFragment(args.skillTitle))
        }

        return binding.root
    }

    private fun changeImageAndBackgroundByLevel(it: Int?) {
        when (it) {
            in ProfileFragment.LEVEL_ZERO_TO_FOUR -> {
                binding.levelUpImageLinear.setBackgroundResource(R.drawable.bg_01)
                binding.levelUpImage.setImageResource(R.drawable.character_01)
            }
            in ProfileFragment.LEVEL_FIVE_TO_NINE -> {
                binding.levelUpImageLinear.setBackgroundResource(R.drawable.bg_02)
                binding.levelUpImage.setImageResource(R.drawable.character_02)
            }
            in ProfileFragment.LEVEL_TEN_TO_FOURTEEN -> {
                binding.levelUpImageLinear.setBackgroundResource(R.drawable.bg_03)
                binding.levelUpImage.setImageResource(R.drawable.character_03)
            }
            in ProfileFragment.LEVEL_FIFTEEN_TO_NINETEEN -> {
                binding.levelUpImageLinear.setBackgroundResource(R.drawable.bg_04)
                binding.levelUpImage.setImageResource(R.drawable.character_04)
            }
            else -> {
                binding.levelUpImageLinear.setBackgroundResource(R.drawable.bg_05)
                binding.levelUpImage.setImageResource(R.drawable.character_05)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUserLevel()

        binding.levelUpBackText.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
