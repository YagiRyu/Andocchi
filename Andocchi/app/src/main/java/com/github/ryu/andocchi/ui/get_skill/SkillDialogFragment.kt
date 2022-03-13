package com.github.ryu.andocchi.ui.get_skill

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.github.ryu.andocchi.databinding.LayoutCustomDialogBinding
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillViewModel

class SkillDialogFragment : DialogFragment() {

    companion object {
        private const val BUNDLE_KEY_TITLE = "bundle_key_title"
        private const val BUNDLE_KEY_MESSAGE = "bundle_key_message"

        private fun newInstance() = SkillDialogFragment()

        private fun newInstance(title: String, message: String): SkillDialogFragment {
            return newInstance().apply {
                arguments = bundleOf(
                    Pair(BUNDLE_KEY_TITLE, title),
                    Pair(BUNDLE_KEY_MESSAGE, message)
                )
            }
        }

        fun show(title: String, message: String, fragmentManager: FragmentManager, tag: String) {
            newInstance(title, message).run {
                show(fragmentManager, tag)
            }
        }
    }

    lateinit var title: String
    lateinit var message: String

    private val viewModel: GetSkillViewModel by viewModels({ requireActivity() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            title = getString(BUNDLE_KEY_TITLE)!!
            message = getString(BUNDLE_KEY_MESSAGE)!!
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") {_, _ ->
                viewModel.state.value = DialogState.OK(this@SkillDialogFragment)
                viewModel.updateUserSkill(message)
            }
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.state.value = DialogState.Cancel(this@SkillDialogFragment)
    }
}

// 通知の状態定義
sealed class DialogState<T : DialogFragment> {
    data class OK<T : DialogFragment>(val dialog: T) : DialogState<T>()
    data class Cancel<T : DialogFragment>(val dialog: T) : DialogState<T>()
}
