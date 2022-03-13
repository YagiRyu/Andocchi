package com.github.ryu.andocchi.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentProfileBinding
import com.github.ryu.andocchi.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        val LEVEL_ZERO_TO_FOUR = 0..4
        val LEVEL_FIVE_TO_NINE = 5..9
        val LEVEL_TEN_TO_FOURTEEN = 10..14
        val LEVEL_FIFTEEN_TO_NINETEEN = 15..19
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.userLevel.observe(viewLifecycleOwner, Observer {
            changeImageAndBackgroundByLevel(it)
        })

        return binding.root
    }

    private fun changeImageAndBackgroundByLevel(it: Int?) {
        when (it) {
            in LEVEL_ZERO_TO_FOUR -> {
                binding.profileConstraint.setBackgroundResource(R.drawable.bg_01)
                binding.andocciImage.setImageResource(R.drawable.character_01)
            }
            in LEVEL_FIVE_TO_NINE -> {
                binding.profileConstraint.setBackgroundResource(R.drawable.bg_02)
                binding.andocciImage.setImageResource(R.drawable.character_02)
            }
            in LEVEL_TEN_TO_FOURTEEN -> {
                binding.profileConstraint.setBackgroundResource(R.drawable.bg_03)
                binding.andocciImage.setImageResource(R.drawable.character_03)
            }
            in LEVEL_FIFTEEN_TO_NINETEEN -> {
                binding.profileConstraint.setBackgroundResource(R.drawable.bg_04)
                binding.andocciImage.setImageResource(R.drawable.character_04)
            }
            else -> {
                binding.profileConstraint.setBackgroundResource(R.drawable.bg_05)
                binding.andocciImage.setImageResource(R.drawable.character_05)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.waitUntilChangedUserName()

        binding.goEditButton.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavProfileToProfileEditFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
