package com.github.ryu.andocchi.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R

class SkillItemViewHolder<BINDING: ViewDataBinding>(val binder: BINDING)
    : RecyclerView.ViewHolder(binder.root) {}

interface ListAdapterItem {
    val id: Long
}
