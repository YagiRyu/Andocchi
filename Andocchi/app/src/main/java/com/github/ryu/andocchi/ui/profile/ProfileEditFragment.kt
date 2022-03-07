package com.github.ryu.andocchi.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.FragmentProfileBinding

class ProfileEditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.actionBar?.title = "編集画面"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile_edit, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}