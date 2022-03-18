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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.ryu.andocchi.databinding.LayoutCustomDialogBinding
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillViewModel

class SkillDialogFragment : DialogFragment() {

    companion object {
        private const val BUNDLE_KEY_TITLE = "bundle_key_title"
        private const val BUNDLE_KEY_MESSAGE = "bundle_key_message"
        private const val BUNDLE_KEY_LIST = "bundle_key_list"
        private const val BUNDLE_KEY_SKILL_TITLE = "bundle_key_skill_title"
        private const val BUNDLE_KEY_LAMBDA = "bundle_key_lambda"

        private fun newInstance() = SkillDialogFragment()

        private fun newInstance(title: String,
                                message: String,
                                skillList: MutableList<String>,
                                skillTitle: String,
                                navigation: () -> Unit,
        ): SkillDialogFragment {
            return newInstance().apply {
                arguments = bundleOf(
                    Pair(BUNDLE_KEY_TITLE, title),
                    Pair(BUNDLE_KEY_MESSAGE, message),
                    Pair(BUNDLE_KEY_LIST, skillList),
                    Pair(BUNDLE_KEY_SKILL_TITLE, skillTitle),
                    Pair(BUNDLE_KEY_LAMBDA, navigation)
                )
            }
        }

        fun show(title: String,
                 message: String,
                 skillList: MutableList<String>,
                 skillTitle: String,
                 fragmentManager: FragmentManager, tag: String,
                 navigation: () -> Unit
        ) {
            newInstance(title, message, skillList, skillTitle, navigation).run {
                show(fragmentManager, tag)
            }
        }
    }

    lateinit var title: String
    lateinit var message: String
    lateinit var skillList: MutableList<String>
    lateinit var skillTitle: String
    lateinit var lambda: (name: String) -> Unit

    private val viewModel: GetSkillViewModel by viewModels({ requireActivity() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            title = getString(BUNDLE_KEY_TITLE)!!
            message = getString(BUNDLE_KEY_MESSAGE)!!
            skillList = getStringArrayList(BUNDLE_KEY_LIST)!!
            skillTitle = getString(BUNDLE_KEY_SKILL_TITLE)!!
            lambda = { name: String ->
                findNavController().navigate(GetSkillFragmentDirections.actionNavSkillToLevelUpFragment(name))
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return if (message in skillList) {
            AlertDialog.Builder(requireActivity())
                .setTitle("スキルを取り消しますか？")
                .setMessage("取り消す")
                .setPositiveButton("OK") {_, _ ->
                    viewModel.state.value = DialogState.OK(this@SkillDialogFragment)
                    viewModel.isContainSkill(message)
                }
                .create()
        } else {
            AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage("習得する")
                .setPositiveButton("OK") {_, _ ->
                    viewModel.state.value = DialogState.OK(this@SkillDialogFragment)
                    viewModel.isContainSkill(message)
                    dismiss()
                    lambda(skillTitle)
                }
                .create()
        }
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
