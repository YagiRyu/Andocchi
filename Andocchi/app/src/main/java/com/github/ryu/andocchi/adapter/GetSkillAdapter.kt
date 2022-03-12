package com.github.ryu.andocchi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.ItemSectionListBinding
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.viewmodel.get_skill.GetSkillViewModel

private object DiffCallback : DiffUtil.ItemCallback<Path>() {
    override fun areItemsTheSame(oldItem: Path, newItem: Path): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Path, newItem: Path): Boolean {
        return oldItem == newItem
    }
}

class GetSkillAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: GetSkillViewModel
) : ListAdapter<Path, GetSkillAdapter.SectionTitleViewHolder>(DiffCallback) {

    class SectionTitleViewHolder(private val binding: ItemSectionListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Path, viewLifecycleOwner: LifecycleOwner, viewModel: GetSkillViewModel) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                section = item
                this.viewmodel = viewModel

                skillCard.setCardBackgroundColor(R.drawable.is_jetpack_gradient_color)

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionTitleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SectionTitleViewHolder(ItemSectionListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: SectionTitleViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }
}
